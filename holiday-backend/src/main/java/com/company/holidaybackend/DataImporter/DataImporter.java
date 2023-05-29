package com.company.holidaybackend.DataImporter;

import com.company.holidaybackend.Service.OfferServiceImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Component
public class DataImporter {

    private static final Logger logger = Logger.getLogger(OfferServiceImpl.class.getName());
    private final ResourceLoader resourceLoader;
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public DataImporter(ResourceLoader resourceLoader, JdbcTemplate jdbcTemplate) {
        this.resourceLoader = resourceLoader;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void importHotels() throws IOException {

        String sql = "SELECT COUNT(*) FROM hotel";

        if (jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class) > 0) {
            return;
        }

        long startTime = System.nanoTime();
        Resource resource = resourceLoader.getResource("classpath:data/hotels.csv");
        InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        Iterable<CSVRecord> records = CSVFormat
                .DEFAULT.withDelimiter(';')
                .withFirstRecordAsHeader()
                .parse(reader);
        List<Object[]> batchArgs = new ArrayList<>();

        for (CSVRecord record : records) {
            int id = Integer.parseInt(record.get("id"));
            String name = record.get("name");
            int stars = Integer.parseInt(String.valueOf(record.get("stars").charAt(0)));
            batchArgs.add(new Object[]{id, name, stars});
        }

        jdbcTemplate.batchUpdate("INSERT INTO hotel (id, name, stars) VALUES (?, ?, ?)", batchArgs);
        long endTime = System.nanoTime();
        double durationSeconds = (endTime - startTime) / 1_000_000_000.0;
        logger.info("Run time insert hotels: " + durationSeconds);
    }

    @PostConstruct
    @Cacheable(cacheNames = "offer")
    public void importOffers() throws IOException {
        String sql = "SELECT COUNT(*) FROM offer";

        if (jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class) > 0) {
            return;
        }

        long startTime = System.nanoTime();
        Resource resource = resourceLoader.getResource("classpath:data/offers.csv");
        InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        Iterable<CSVRecord> records = CSVFormat
                .DEFAULT.withDelimiter(',')
                .withFirstRecordAsHeader()
                .parse(reader);

        List<Object[]> batchArgs = new ArrayList<>();
        int batchSize = 100000;
        int counter = 1;

        for (CSVRecord record : records) {

            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

            int id = Integer.parseInt(record.get("hotelid"));
            int countAdults = Integer.parseInt(record.get("countadults"));
            int countChildren = Integer.parseInt(record.get("countchildren"));
            double price = Double.parseDouble(record.get("price"));
            String outboundDepartureAirport = record.get("outbounddepartureairport");

            OffsetDateTime inboundDepartureDatetime = OffsetDateTime.parse(record.get("inbounddeparturedatetime"), formatter);
            OffsetDateTime inboundArrivalDatetime = OffsetDateTime.parse(record.get("inboundarrivaldatetime"), formatter);
            OffsetDateTime outboundDepartureDatetime = OffsetDateTime.parse(record.get("outbounddeparturedatetime"), formatter);
            OffsetDateTime outboundArrivalDatetime = OffsetDateTime.parse(record.get("outboundarrivaldatetime"), formatter);

            String mro = record.get("mro");

            batchArgs.add(new Object[]{id, countAdults, countChildren, price,
                    outboundDepartureAirport,
                    inboundDepartureDatetime, inboundArrivalDatetime,
                    outboundDepartureDatetime, outboundArrivalDatetime,
                    mro});

            if (batchArgs.size() == batchSize) {
                logger.info("UPDATE: num of imported rows: " + batchSize * counter++);
                jdbcTemplate.batchUpdate("INSERT INTO offer (" +
                        "hotel_id, count_adults, count_children, price," +
                        "outbound_departure_airport," +
                        "inbound_departure_datetime , inbound_arrival_datetime," +
                        "outbound_departure_datetime,outbound_arrival_datetime," +
                        "mro" +
                        ") VALUES (?,?,?,?,?,?,?,?,?,?)", batchArgs);
                batchArgs.clear();
            }
        }
        jdbcTemplate.batchUpdate("INSERT INTO offer (" +
                "hotel_id, count_adults, count_children, price," +
                "outbound_departure_airport," +
                "inbound_departure_datetime , inbound_arrival_datetime," +
                "outbound_departure_datetime, outbound_arrival_datetime," +
                "mro" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?)", batchArgs);

        // end time, print total runtime
        long endTime = System.nanoTime();
        double durationSeconds = (endTime - startTime) / 1_000_000_000.0;
        logger.info("Run time insert offers: " + durationSeconds);
    }
}

