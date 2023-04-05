package com.company.holidaybackend.Model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;
    private int hotelId;
    private OffsetDateTime outboundDepartureDatetime;
    private OffsetDateTime inboundDepartureDatetime;
    private int countAdults;
    private int countChildren;
    private double price;
    @Transient
    private long duration;
    @Column(length = 3)
    private String inboundDepartureAirport;
    @Column(length = 3)
    private String inboundArrivalAirport;
    private OffsetDateTime inboundArrivalDatetime;
    @Column(length = 3)
    private String outboundDepartureAirport;
    @Column(length = 3)
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
        //this.duration = Duration.between(this.inboundArrivalDatetime, this.outboundDepartureDatetime).toDays();
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

    public long getDuration() {
        return Duration.between(this.inboundArrivalDatetime, this.outboundDepartureDatetime).toDays();
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Offer() {
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public OffsetDateTime getOutboundDepartureDatetime() {
        return outboundDepartureDatetime;
    }

    public void setOutboundDepartureDatetime(String outboundDepartureDatetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        this.outboundDepartureDatetime = OffsetDateTime.parse(outboundDepartureDatetime, formatter);
    }

    public OffsetDateTime getInboundDepartureDatetime() {
        return inboundDepartureDatetime;
    }

    public void setInboundDepartureDatetime(String inboundDepartureDatetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        this.inboundDepartureDatetime = OffsetDateTime.parse(inboundDepartureDatetime, formatter);
    }

    public int getCountAdults() {
        return countAdults;
    }

    public void setCountAdults(int countAdults) {
        this.countAdults = countAdults;
    }

    public int getCountChildren() {
        return countChildren;
    }

    public void setCountChildren(int countChildren) {
        this.countChildren = countChildren;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInboundDepartureAirport() {
        return inboundDepartureAirport;
    }

    public void setInboundDepartureAirport(String inboundDepartureAirport) {
        this.inboundDepartureAirport = inboundDepartureAirport;
    }

    public String getInboundArrivalAirport() {
        return inboundArrivalAirport;
    }

    public void setInboundArrivalAirport(String inboundArrivalAirport) {
        this.inboundArrivalAirport = inboundArrivalAirport;
    }

    public OffsetDateTime getInboundArrivalDatetime() {
        return inboundArrivalDatetime;
    }

    public void setInboundArrivalDatetime(String inboundArrivalDatetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        this.inboundArrivalDatetime = OffsetDateTime.parse(inboundArrivalDatetime, formatter);
    }

    public String getOutboundDepartureAirport() {
        return outboundDepartureAirport;
    }

    public void setOutboundDepartureAirport(String outboundDepartureAirport) {
        this.outboundDepartureAirport = outboundDepartureAirport;
    }

    public String getOutboundArrivalAirport() {
        return outboundArrivalAirport;
    }

    public void setOutboundArrivalAirport(String outboundArrivalAirport) {
        this.outboundArrivalAirport = outboundArrivalAirport;
    }

    public OffsetDateTime getOutboundArrivalDatetime() {
        return outboundArrivalDatetime;
    }

    public void setOutboundArrivalDatetime(String outboundArrivalDatetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        this.outboundArrivalDatetime = OffsetDateTime.parse(outboundArrivalDatetime, formatter);
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public boolean isOceanView() {
        return oceanView;
    }

    public void setOceanView(boolean oceanView) {
        this.oceanView = oceanView;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
