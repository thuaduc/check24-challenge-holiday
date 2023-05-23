package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.Cart;
import com.company.holidaybackend.Model.Offer;

import java.util.List;

public interface CartService {
    List<Offer> getAllOffer();

    boolean addToCart(Integer id);

    boolean remove(Integer id);
}
