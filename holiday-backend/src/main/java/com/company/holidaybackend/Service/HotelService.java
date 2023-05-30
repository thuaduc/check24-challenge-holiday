package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.Hotel;

import java.util.Optional;


public interface HotelService {
    Optional<Hotel> getHotelById(int id);
}
