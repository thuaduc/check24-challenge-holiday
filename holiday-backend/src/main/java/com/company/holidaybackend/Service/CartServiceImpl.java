package com.company.holidaybackend.Service;

import com.company.holidaybackend.Model.Cart;
import com.company.holidaybackend.Model.Offer;
import com.company.holidaybackend.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Offer> getAllOffer() {
        return cartRepository.getAllCart();
    }

    @Override
    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }
    
    @Override
    public boolean exist(Integer id) {
        return cartRepository.existsById(id);
    }

    @Override
    public void remove(Integer id) {
        cartRepository.deleteById(id);
    }


}
