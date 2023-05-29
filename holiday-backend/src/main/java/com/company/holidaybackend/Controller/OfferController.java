package com.company.holidaybackend.Controller;


import com.company.holidaybackend.Model.HotelList;
import com.company.holidaybackend.Model.Offer;
import com.company.holidaybackend.Service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class OfferController {
    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/list")
    public List<HotelList> get_hotel_list_and_min_price(@RequestParam(value = "search") String search) {
        return offerService.query_and_return_min_price(search);

    }

    @GetMapping("/offer")
    public List<Offer> get_offers(@RequestParam(value = "search") String search) {
        return offerService.query_and_return_offers(search);
    }
}
