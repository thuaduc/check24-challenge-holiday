package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.Offer;
import com.company.holidaybackend.Search.OfferSpecification;
import com.company.holidaybackend.Repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public String saveOffer(Offer offer) {
        offerRepository.save(offer);
        return "Offer saved successfully!";
    }

    @Override
    public List<Offer> getAllOffer() {
        return offerRepository.findAll();
    }

    @Override
    public List<Offer> findOffer(Specification<Offer> spec){
        return offerRepository.findAll(spec);
    }

    @Override public List<Offer> findAll(){
        return offerRepository.findAll();
    }
}
