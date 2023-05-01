package com.company.holidaybackend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    private int hotelId;

    private int countAdults;
    private int countChildren;
    private double price;

    private long duration;

    private String inboundDepartureAirport;

    private java.sql.Timestamp inboundDepartureDatetime;

    private String inboundArrivalAirport;

    private java.sql.Timestamp inboundArrivalDatetime;

    private String outboundDepartureAirport;

    private java.sql.Timestamp outboundDepartureDatetime;

    private String outboundArrivalAirport;

    private java.sql.Timestamp outboundArrivalDatetime;

    private String mealType;
    private boolean oceanView;
    private String roomType;

    public Offer(int hotelId, int countAdults, int countChildren, double price,
                 String inboundDepartureDatetime,
                 String inboundArrivalDatetime,
                 String outboundDepartureAirport,
                 String outboundDepartureDatetime,
                 String outboundArrivalAirport, String outboundArrivalDatetime,
                 String mealType, boolean oceanView, String roomType) {


        this.hotelId = hotelId;
        this.outboundDepartureDatetime = timestamp_converter(outboundDepartureDatetime);
        this.inboundDepartureDatetime = timestamp_converter(inboundDepartureDatetime);
        this.countAdults = countAdults;
        this.countChildren = countChildren;
        this.price = price;
        long milliseconds = this.outboundArrivalDatetime.getTime() - this.inboundDepartureDatetime.getTime();
        this.duration = (int) milliseconds / (24 * 60 * 60 * 1000);
        this.inboundArrivalAirport = "PMI";
        this.outboundDepartureAirport = "PMI";
        this.inboundArrivalDatetime = timestamp_converter(inboundArrivalDatetime);
        this.outboundArrivalDatetime = timestamp_converter(outboundArrivalDatetime);
        this.outboundArrivalAirport = outboundArrivalAirport;
        this.mealType = mealType;
        this.oceanView = oceanView;
        this.roomType = roomType;
    }

    private java.sql.Timestamp timestamp_converter(String timestamp) {
        LocalDateTime localDateTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
        return java.sql.Timestamp.from(zonedDateTime.toInstant());
    }
}
