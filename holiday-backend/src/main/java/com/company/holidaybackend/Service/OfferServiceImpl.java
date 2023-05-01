package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.HotelList;
import com.company.holidaybackend.Model.Offer;
import com.company.holidaybackend.Repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<HotelList> query_and_return_min_price(String inboundDepartureAirport, java.sql.Timestamp inboundDepartureDateTime,
                                                      String outboundArrivalAirport, java.sql.Timestamp outboundArrivalDatetime,
                                                      int countAdults, int countChildren, int duration,
                                                      String mealType, String roomType, String oceanView) {

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

        return offerRepository.query_and_return_min_price(inboundDepartureAirport, inboundDepartureDateTime,
                outboundArrivalAirport, outboundArrivalDatetime,
                countAdults, countChildren, duration,
                mealType, roomType, oceanView
        );
    }

    @Override
    public List<Offer> query_and_return_offers(int id, String inboundDepartureAirport, java.sql.Timestamp inboundDepartureDatetime,
                                               String outboundArrivalAirport, java.sql.Timestamp outboundArrivalDatetime,
                                               int countAdults, int countChildren, int duration,
                                               String mealType, String roomType, String oceanView) {
        return offerRepository.query_and_return_offers(id, inboundDepartureAirport, inboundDepartureDatetime,
                outboundArrivalAirport, outboundArrivalDatetime,
                countAdults, countChildren, duration,
                mealType, roomType, oceanView);
    }


}


