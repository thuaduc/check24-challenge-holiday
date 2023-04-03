package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.Hotel;

import java.util.List;


public interface HotelService {
    public String addHotel(Hotel hotel);
    public List<Hotel> getHotel();

    public String deleteHotel(int id);
}
