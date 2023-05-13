package com.company.holidaybackend.Controller;


import com.company.holidaybackend.Model.HotelList;
import com.company.holidaybackend.Model.Offer;
import com.company.holidaybackend.Service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class OfferController {
    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
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

    private Map<String, String> hadle_query(String search) {
        String[] queryParameters = search.split(",");
        Map<String, String> queryParams = new HashMap<>();
        for (String parameter : queryParameters) {
            String[] keyValue = parameter.split("==");
            queryParams.put(keyValue[0], keyValue[1]);
        }
        return queryParams;
    }

    @GetMapping("/list")
    public List<HotelList> get_hotel_list_and_min_price(@RequestParam(value = "search") String search) {

        Map<String, String> queryParams = hadle_query(search);

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
        int duration =
                Integer.parseInt(queryParams.getOrDefault("duration", "0"));

        return offerService.query_and_return_min_price(
                outboundDepartureAirport, outboundDepartureDatetime,
                inboundArrivalDatetime,
                countAdults, countChildren, duration
        );

    }

    @GetMapping("/offer")
    public List<Offer> get_offers(@RequestParam(value = "search") String search) {
        Map<String, String> queryParams = hadle_query(search);

        int id = Integer.parseInt(queryParams.getOrDefault("hotelId", "0"));

        String outboundDepartureAirport = queryParams.getOrDefault("outboundDepartureAirport", null);

        java.sql.Timestamp outboundDepartureDatetime = timestamp_converter(
                queryParams.getOrDefault("outboundDepartureDatetime", null));
        System.out.println(outboundDepartureDatetime);

        java.sql.Timestamp inboundArrivalDatetime = timestamp_converter(
                queryParams.getOrDefault("inboundArrivalDatetime", null));
        System.out.println(inboundArrivalDatetime);

        int countAdults = Integer.parseInt(queryParams.getOrDefault("countAdults", "0"));
        int countChildren = Integer.parseInt(queryParams.getOrDefault("countChildren", "0"));
        int duration = Integer.parseInt(queryParams.getOrDefault("duration", "0"));

        if (id == 0) {
            return new ArrayList<>();
        }

        return offerService.query_and_return_offers(
                id, outboundDepartureAirport, outboundDepartureDatetime,
                inboundArrivalDatetime,
                countAdults, countChildren, duration
        );
    }
}
