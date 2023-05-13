package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.HotelList;
import com.company.holidaybackend.Model.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    List<HotelList> query_and_return_min_price(String outboundDepartureAirport, java.sql.Timestamp outboundDepartureDatetime,
                                               java.sql.Timestamp inboundArrivalDatetime,
                                               int countAdults, int countChildren, int duration);


    List<Offer> query_and_return_offers(int id, String outboundDepartureAirport, java.sql.Timestamp outboundDepartureDatetime,
                                        java.sql.Timestamp inboundArrivalDatetime,
                                        int countAdults, int countChildren, int duration);

    Optional<Offer> getOfferById(Integer id);
}
