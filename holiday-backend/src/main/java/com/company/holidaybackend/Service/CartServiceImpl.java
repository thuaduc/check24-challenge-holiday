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
    public boolean addToCart(Integer id) {
        if (cartRepository.getCartIdByOfferId(id).size() == 0) {
            cartRepository.save(new Cart(id));
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Integer id) {
        if (cartRepository.getCartIdByOfferId(id).size() > 0) {
            cartRepository.deleteById(cartRepository.getCartIdByOfferId(id).get(0));
            return true;
        }
        return false;
    }

}
