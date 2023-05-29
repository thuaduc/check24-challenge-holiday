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

    private java.sql.Timestamp inboundDepartureDatetime;

    private java.sql.Timestamp inboundArrivalDatetime;

    private String outboundDepartureAirport;

    private java.sql.Timestamp outboundDepartureDatetime;

    private java.sql.Timestamp outboundArrivalDatetime;

    private String mro;

    public Offer(int hotelId, int countAdults, int countChildren, double price,
                 String outboundDepartureAirport,
                 String inboundDepartureDatetime,
                 String inboundArrivalDatetime,
                 String outboundDepartureDatetime,
                 String outboundArrivalDatetime,
                 String mro) {


        this.hotelId = hotelId;
        this.outboundDepartureAirport = outboundDepartureAirport;
        this.outboundDepartureDatetime = timestamp_converter(outboundDepartureDatetime);
        this.inboundDepartureDatetime = timestamp_converter(inboundDepartureDatetime);
        this.countAdults = countAdults;
        this.countChildren = countChildren;
        this.price = price;
        this.inboundArrivalDatetime = timestamp_converter(inboundArrivalDatetime);
        this.outboundArrivalDatetime = timestamp_converter(outboundArrivalDatetime);
        this.mro = mro;
    }

    private java.sql.Timestamp timestamp_converter(String timestamp) {
        LocalDateTime localDateTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
        return java.sql.Timestamp.from(zonedDateTime.toInstant());
    }
}
