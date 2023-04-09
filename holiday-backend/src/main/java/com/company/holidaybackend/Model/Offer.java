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
    private String inboundArrivalAirport;
    private OffsetDateTime inboundArrivalDatetime;

    private OffsetDateTime inboundDepartureDatetime;
    private OffsetDateTime outboundDepartureDatetime;
    private String outboundDepartureAirport;
    private String outboundArrivalAirport;
    private OffsetDateTime outboundArrivalDatetime;

    private String mealType;
    private boolean oceanView;
    private String roomType;

    public Offer(int hotelId, String outboundDepartureDatetime, String inboundDepartureDatetime,
                 int countAdults, int countChildren, double price, String inboundDepartureAirport,
                 String inboundArrivalAirport, String inboundArrivalDatetime,
                 String outboundDepartureAirport, String outboundArrivalAirport,
                 String outboundArrivalDatetime, String mealType, boolean oceanView, String roomType) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        this.hotelId = hotelId;
        this.outboundDepartureDatetime = OffsetDateTime.parse(outboundDepartureDatetime, formatter);
        this.inboundDepartureDatetime = OffsetDateTime.parse(inboundDepartureDatetime, formatter);
        this.countAdults = countAdults;
        this.countChildren = countChildren;
        this.price = price;
        this.duration = Duration.between(this.inboundArrivalDatetime, this.outboundDepartureDatetime).toDays();
        this.inboundDepartureAirport = inboundDepartureAirport;
        this.inboundArrivalAirport = inboundArrivalAirport;
        this.inboundArrivalDatetime = OffsetDateTime.parse(inboundArrivalDatetime, formatter);
        this.outboundDepartureAirport = outboundDepartureAirport;
        this.outboundArrivalAirport = outboundArrivalAirport;
        this.outboundArrivalDatetime = OffsetDateTime.parse(outboundArrivalDatetime, formatter);
        this.mealType = mealType;
        this.oceanView = oceanView;
        this.roomType = roomType;
    }

}
