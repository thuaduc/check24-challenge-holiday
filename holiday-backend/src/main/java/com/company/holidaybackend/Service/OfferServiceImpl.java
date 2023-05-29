package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.HotelList;
import com.company.holidaybackend.Model.Offer;
import com.company.holidaybackend.Repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

@Service
public class OfferServiceImpl implements OfferService {

    private static final Logger logger = Logger.getLogger(OfferServiceImpl.class.getName());
    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    private Map<String, String> handle_query(String search) {
        String[] queryParameters = search.split(",");
        Map<String, String> queryParams = new HashMap<>();
        for (String parameter : queryParameters) {
            String[] keyValue = parameter.split("=");
            queryParams.put(keyValue[0], keyValue[1]);
        }
        return queryParams;
    }

    private java.sql.Timestamp timestamp_converter(String timestamp) {
        if (timestamp == null) {
            return null;
        }
        String new_time = timestamp.replace(" ", "+");
        LocalDateTime localDateTime = LocalDateTime.parse(new_time, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
        return java.sql.Timestamp.from(zonedDateTime.toInstant());
    }

    @Override
    @Cacheable("list")
    public List<HotelList> query_and_return_min_price(String search) {
        logger.info("Get Hotel List: " + search);
        Map<String, String> queryParams = handle_query(search);

        String outboundDepartureAirport =
                queryParams.getOrDefault("outboundDepartureAirport", null);

        java.sql.Timestamp outboundDepartureDatetime = timestamp_converter(
                queryParams.getOrDefault("outboundDepartureDatetime", null));

        java.sql.Timestamp inboundArrivalDatetime = timestamp_converter(
                queryParams.getOrDefault("inboundArrivalDatetime", null));

        int countAdults =
                Integer.parseInt(queryParams.getOrDefault("countAdults", "0"));
        int countChildren =
                Integer.parseInt(queryParams.getOrDefault("countChildren", "0"));

        return offerRepository.query_and_return_min_price(
                outboundDepartureAirport, outboundDepartureDatetime,
                inboundArrivalDatetime,
                countAdults, countChildren
        );
    }

    @Override
    @Cacheable("offer")
    public List<Offer> query_and_return_offers(String search) {

        Map<String, String> queryParams = handle_query(search);

        int id = Integer.parseInt(queryParams.getOrDefault("id", "0"));
        if (id == 0) {
            return new ArrayList<>();
        }

        String outboundDepartureAirport = queryParams.getOrDefault("outboundDepartureAirport", null);

        java.sql.Timestamp outboundDepartureDatetime = timestamp_converter(
                queryParams.getOrDefault("outboundDepartureDatetime", null));
        System.out.println(outboundDepartureDatetime);

        java.sql.Timestamp inboundArrivalDatetime = timestamp_converter(
                queryParams.getOrDefault("inboundArrivalDatetime", null));
        System.out.println(inboundArrivalDatetime);

        int countAdults = Integer.parseInt(queryParams.getOrDefault("countAdults", "0"));
        int countChildren = Integer.parseInt(queryParams.getOrDefault("countChildren", "0"));


        return offerRepository.query_and_return_offers(
                id, outboundDepartureAirport, outboundDepartureDatetime,
                inboundArrivalDatetime,
                countAdults, countChildren);
    }

}


