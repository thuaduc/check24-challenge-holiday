package com.company.holidaybackend.Controller;


import com.company.holidaybackend.Model.HotelList;
import com.company.holidaybackend.Model.Offer;
import com.company.holidaybackend.Service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        LocalDateTime localDateTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
        return java.sql.Timestamp.from(zonedDateTime.toInstant());
    }

    @GetMapping("/list")
    public List<HotelList> get_hotel_list_and_min_price(@RequestParam(value = "search") String search) {
        String[] queryParameters = search.split(",");
        Map<String, String> queryParams = new HashMap<>();
        for (String parameter : queryParameters) {
            String[] keyValue = parameter.split(":");
            queryParams.put(keyValue[0], keyValue[1]);
        }

        String inboundDepartureAirport = queryParams.getOrDefault("inboundDepartureAirport", null);
        String outboundArrivalAirport = queryParams.getOrDefault("outboundArrivalAirport", null);

        java.sql.Timestamp inboundDepartureDateTime = timestamp_converter(
                queryParams.getOrDefault("inboundDepartureDatetime", null));
        java.sql.Timestamp outboundArrivalDatetime = timestamp_converter(
                queryParams.getOrDefault("outboundArrivalDatetime", null));

        int countAdults = Integer.parseInt(queryParams.getOrDefault("countAdults", "0"));
        int countChildren = Integer.parseInt(queryParams.getOrDefault("countChildren", "0"));
        int duration = Integer.parseInt(queryParams.getOrDefault("duration", "0"));

        String mealType = queryParams.getOrDefault("mealType", null);
        String roomType = queryParams.getOrDefault("roomType", null);
        String oceanView = queryParams.getOrDefault("oceanView", null);

        System.out.println("-------------------------------");
        System.out.println(inboundDepartureAirport);
        System.out.println(inboundDepartureDateTime);
        System.out.println(outboundArrivalAirport);
        System.out.println(outboundArrivalDatetime);
        System.out.println(countAdults);
        System.out.println(countChildren);
        System.out.println(duration);
        System.out.println(mealType);
        System.out.println(roomType);
        System.out.println(oceanView);

        return offerService.query_and_return_min_price(
                inboundDepartureAirport, inboundDepartureDateTime,
                outboundArrivalAirport, outboundArrivalDatetime,
                countAdults, countChildren, duration,
                mealType, roomType, oceanView
        );

    }

    @GetMapping("/offer")
    public List<Offer> get_offers(@RequestParam(value = "search") String search) {
        System.out.println(search);
        String[] queryParameters = search.split(",");
        Map<String, String> queryParams = new HashMap<>();
        for (String parameter : queryParameters) {
            String[] keyValue = parameter.split(":");
            queryParams.put(keyValue[0], keyValue[1]);
        }

        int id = Integer.parseInt(queryParams.getOrDefault("hotelId", "0"));

        String inboundDepartureAirport = queryParams.getOrDefault("inboundDepartureAirport", null);
        String outboundArrivalAirport = queryParams.getOrDefault("outboundArrivalAirport", null);

        java.sql.Timestamp inboundDepartureDatetime = timestamp_converter(
                queryParams.getOrDefault("inboundDepartureDatetime", null));
        java.sql.Timestamp outboundArrivalDatetime = timestamp_converter(
                queryParams.getOrDefault("outboundArrivalDatetime", null));

        int countAdults = Integer.parseInt(queryParams.getOrDefault("countAdults", "0"));
        int countChildren = Integer.parseInt(queryParams.getOrDefault("countChildren", "0"));
        int duration = Integer.parseInt(queryParams.getOrDefault("duration", "0"));

        String mealType = queryParams.getOrDefault("mealType", null);
        String roomType = queryParams.getOrDefault("roomType", null);
        String oceanView = queryParams.getOrDefault("oceanView", null);

        if (id == 0) {
            return new ArrayList<>();
        }

        return offerService.query_and_return_offers(id,
                inboundDepartureAirport, inboundDepartureDatetime,
                outboundArrivalAirport, outboundArrivalDatetime,
                countAdults, countChildren, duration,
                mealType, roomType, oceanView
        );


    }


    @GetMapping
    public String welcome() {
        return "Welcome to Check24 Challenge Holiday Api";
    }
}
