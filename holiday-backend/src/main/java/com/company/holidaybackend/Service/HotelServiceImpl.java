package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.Hotel;
import com.company.holidaybackend.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Optional<Hotel> getHotelById(int id) {
        return this.hotelRepository.findById(id);
    }

    @Override
    public List<Hotel> getBestHotels() {
        return hotelRepository.queryBestHotel();
    }
}
