package com.company.holidaybackend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table
@Getter @Setter @NoArgsConstructor
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
    private OffsetDateTime inboundDepartureDatetime;

    private String inboundArrivalAirport;
    private OffsetDateTime inboundArrivalDatetime;

    private String outboundDepartureAirport;
    private OffsetDateTime outboundDepartureDatetime;

    private String outboundArrivalAirport;
    private OffsetDateTime outboundArrivalDatetime;

    private String mealType;
    private boolean oceanView;
    private String roomType;

    public Offer(int hotelId, int countAdults, int countChildren, double price,
                 String inboundDepartureAirport, String inboundDepartureDatetime,
                 String inboundArrivalDatetime,
                 String outboundDepartureDatetime,
                 String outboundArrivalAirport, String outboundArrivalDatetime,
                 String mealType, boolean oceanView, String roomType) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        this.hotelId = hotelId;
        this.outboundDepartureDatetime = OffsetDateTime.parse(outboundDepartureDatetime, formatter);
        this.inboundDepartureDatetime = OffsetDateTime.parse(inboundDepartureDatetime, formatter);
        this.countAdults = countAdults;
        this.countChildren = countChildren;
        this.price = price;
        this.duration = Duration.between(this.inboundArrivalDatetime, this.outboundDepartureDatetime).toDays();
        this.inboundDepartureAirport = inboundDepartureAirport;
        this.inboundArrivalAirport = "PMI";
        this.inboundArrivalDatetime = OffsetDateTime.parse(inboundArrivalDatetime, formatter);
        this.outboundDepartureAirport = "PMI";
        this.outboundArrivalAirport = outboundArrivalAirport;
        this.outboundArrivalDatetime = OffsetDateTime.parse(outboundArrivalDatetime, formatter);
        this.mealType = mealType;
        this.oceanView = oceanView;
        this.roomType = roomType;
    }

}
