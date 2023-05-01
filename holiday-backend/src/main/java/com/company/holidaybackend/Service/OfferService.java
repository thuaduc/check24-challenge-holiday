package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.HotelList;
import com.company.holidaybackend.Model.Offer;

import java.util.List;

public interface OfferService {

    List<HotelList> query_and_return_min_price(String inboundDepartureAirport,
                                                      java.sql.Timestamp inboundDepartureDateTime,
                                                      String outboundArrivalAirport,
                                                      java.sql.Timestamp outboundArrivalDatetime,
                                                      int countAdults, int countChildren, int duration,
                                                      String mealType, String roomType, String oceanView);


    List<Offer> query_and_return_offers(int id,
                                        String inboundDepartureAirport,
                                        java.sql.Timestamp inboundDepartureDatetime,
                                        String outboundArrivalAirport,
                                        java.sql.Timestamp outboundArrivalDatetime,
                                        int countAdults, int countChildren, int duration,
                                        String mealType, String roomType, String oceanView);
}
