package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.HotelList;
import com.company.holidaybackend.Model.Offer;
import com.company.holidaybackend.Repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<HotelList> query_and_return_min_price(String outboundDepartureAirport,
                                                      java.sql.Timestamp outboundDepartureDatetime,
                                                      java.sql.Timestamp inboundArrivalDatetime,
                                                      int countAdults, int countChildren, int duration) {


        return offerRepository.query_and_return_min_price(
                outboundDepartureAirport, outboundDepartureDatetime,
                inboundArrivalDatetime,
                countAdults, countChildren, duration
        );
    }

    @Override
    public List<Offer> query_and_return_offers(int id,
                                               String outboundDepartureAirport,
                                               java.sql.Timestamp outboundDepartureDatetime,
                                               java.sql.Timestamp inboundArrivalDatetime,
                                               int countAdults, int countChildren, int duration) {
        return offerRepository.query_and_return_offers(
                id, outboundDepartureAirport, outboundDepartureDatetime,
                inboundArrivalDatetime,
                countAdults, countChildren, duration);
    }

    @Override
    public Optional<Offer> getOfferById(Integer id) {
        return offerRepository.findById(id);
    }


}


