package com.company.holidaybackend.DataImporter;

import com.company.holidaybackend.Repository.HotelRepository;
import com.company.holidaybackend.Repository.OfferRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataImporter {

    private final ResourceLoader resourceLoader;
    private final JdbcTemplate jdbcTemplate;

    private final long lastModifiedTime_hotels = 0L;
    private final long lastModifiedTime_offers = 0L;

    @Value("${hotels.file.path}")
    private String hotelsFilePath;

    @Value("${offers.file.path}")
    private String offersFilePath;

    @Autowired
    public DataImporter(ResourceLoader resourceLoader, JdbcTemplate jdbcTemplate) {
        this.resourceLoader = resourceLoader;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void importHotels() throws IOException {

        String sql = "SELECT COUNT(*) FROM hotel";

        int count = jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);

        if (count > 0) {
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
        System.out.println("==========> run time insert hotels: " + durationSeconds);
    }

    @PostConstruct
    public void importOffers() throws IOException {
        String sql = "SELECT COUNT(*) FROM offer";

        int count = jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);

        if (count > 0) {
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

            var duration = Duration.between(outboundDepartureDatetime, inboundArrivalDatetime).toDays();

            String mealType = record.get("mealtype");

            boolean oceanView = Boolean.parseBoolean(record.get("oceanview"));

            String roomType = record.get("roomtype");

            batchArgs.add(new Object[]{id, countAdults, countChildren, price, duration,
                    outboundDepartureAirport,
                    inboundDepartureDatetime, inboundArrivalDatetime,
                    outboundDepartureDatetime, outboundArrivalDatetime,
                    mealType, oceanView, roomType});

        }

        jdbcTemplate.batchUpdate("INSERT INTO offer (" +
                "hotel_id, count_adults, count_children, price, duration," +
                "outbound_departure_airport," +
                "inbound_departure_datetime , inbound_arrival_datetime," +
                "outbound_departure_datetime,outbound_arrival_datetime," +
                "meal_type, ocean_view, room_type" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", batchArgs);

        long endTime = System.nanoTime();
        double durationSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("==========> run time insert offers: " + durationSeconds);
    }
}

