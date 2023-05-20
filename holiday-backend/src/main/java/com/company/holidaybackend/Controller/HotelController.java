package com.company.holidaybackend.Controller;

import com.company.holidaybackend.Model.Hotel;
import com.company.holidaybackend.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotel")
    public String getHotel() {
        List<Hotel> hotels = hotelService.getAllHotel();
        StringBuilder result = new StringBuilder();
        for (Hotel hotel : hotels) {
            result.append(hotel.toString()).append("\n");
        }
        return result.toString();
    }

    @GetMapping("/hotel/{id}")
    public Optional<Hotel> getHotelById(@PathVariable("id") int id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping("hotel/best")
    public List<Hotel> getBestHotels() {
        return hotelService.getBestHotels();
    }

}
