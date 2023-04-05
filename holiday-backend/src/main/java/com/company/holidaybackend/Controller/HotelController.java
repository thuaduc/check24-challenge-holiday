package com.company.holidaybackend.Controller;

import com.company.holidaybackend.Model.Hotel;
import com.company.holidaybackend.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotel")
    public String getHotel(){
        List<Hotel> hotels = hotelService.getAllHotel();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < hotels.size(); i++){
            result.append(hotels.get(i).toString() + "\n");
        }
        return result.toString();
    }

    @GetMapping("/hotel/{id}")
    public Optional<Hotel> getHotelById(@PathVariable("id") int id){
        Optional<Hotel> result = hotelService.getHotelById(id);
        return result;
    }


}
