package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.HotelList;
import com.company.holidaybackend.Model.Offer;

import java.util.List;

public interface OfferService {

    List<HotelList> query_and_return_min_price(String search);


    List<Offer> query_and_return_offers(String search);

}
