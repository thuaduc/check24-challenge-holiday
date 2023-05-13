package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.Cart;
import com.company.holidaybackend.Model.Offer;

import java.util.List;

public interface CartService {
    List<Offer> getAllOffer();

    void addToCart(Cart cart);

    boolean exist(Integer id);

    void remove(Integer id);
}
