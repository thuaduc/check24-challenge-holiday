package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.Offer;
import com.company.holidaybackend.Search.OfferSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface OfferService {
    String saveOffer(Offer offer);

    List<Offer> getAllOffer();

    List<Offer> findOffer(Specification<Offer> spec);

    List<Offer> findAll();
}
