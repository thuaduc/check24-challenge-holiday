package com.company.holidaybackend.Controller;

import com.company.holidaybackend.Model.Cart;
import com.company.holidaybackend.Model.Offer;
import com.company.holidaybackend.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart/{id}")
    public String addToCard(@PathVariable("id") Integer id) {
        cartService.addToCart(new Cart(id));
        return "Offer added to Card";
    }

    @GetMapping("/cart")
    public List<Offer> getAllCard() {
        return cartService.getAllOffer();
    }

    @DeleteMapping("/cart{id}")
    public String removeFromCart(@PathVariable("id") Integer id) {
        if (cartService.exist(id)) {
            cartService.remove(id);
            return "Offer added to Cart";
        }
        return "Error while adding Offer to Card: Offer id does not exist";
    }

}
