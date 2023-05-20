package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.Hotel;

import java.util.List;
import java.util.Optional;


public interface HotelService {
    List<Hotel> getAllHotel();

    Optional<Hotel> getHotelById(int id);

    List<Hotel> getBestHotels();

}
