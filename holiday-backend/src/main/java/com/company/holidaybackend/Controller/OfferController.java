package com.company.holidaybackend.Controller;


import com.company.holidaybackend.Model.Offer;
import com.company.holidaybackend.Search.OfferSpecificationBuilder;
import com.company.holidaybackend.Service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/offer")
@CrossOrigin
public class OfferController {
    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/offer")
    public String addOffer(@RequestBody Offer offer){
        offerService.saveOffer(offer);
        return "Offer added successfully!";
    }

    @GetMapping("/offer")
    public List<Offer> search(@RequestParam(value = "search") String search) {
        OfferSpecificationBuilder builder = new OfferSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<Offer> spec = builder.build();
        return offerService.findOffer(spec);
    }
}
