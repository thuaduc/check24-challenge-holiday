package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.Hotel;
import com.company.holidaybackend.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public String addHotel(Hotel hotel) {
        /*if (hotelRepository.existsById(hotel.getId())){
            return "Can not create, hotel existed";
        } else {
            hotelRepository.save(hotel);
            return "Hotel added successfully";
        }*/
        hotelRepository.save(hotel);
        return hotel + " added";
    }

    @Override
    public List<Hotel> getHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public String deleteHotel(int id) {
        hotelRepository.deleteById(id);
        return "Deleted";
    }
}
