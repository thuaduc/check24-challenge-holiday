package com.company.holidaybackend.Controller;

import com.company.holidaybackend.Model.Hotel;
import com.company.holidaybackend.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/get")
    public String getHotel(){
        List<Hotel> hotels = hotelService.getHotel();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < hotels.size(); i++){
            result.append(hotels.get(i).toString() + "\n");
        }
        return result.toString();
    }
    @PostMapping("/post")
    public String addHotel(@RequestBody Hotel hotel){
        return hotelService.addHotel(hotel);
    }

    @DeleteMapping("/delete")
    public String deleteHotel(@PathVariable int id){
        return hotelService.deleteHotel(id);
    }
}
