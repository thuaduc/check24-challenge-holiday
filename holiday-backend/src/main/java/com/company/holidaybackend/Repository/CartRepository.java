package com.company.holidaybackend.Repository;

import com.company.holidaybackend.Model.Cart;
import com.company.holidaybackend.Model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query(value = "SELECT o from Offer o WHERE EXISTS (SELECT c FROM Cart c where c.offerId = o.offerId)")
    List<Offer> getAllCart();

    @Query(value = "SELECT c.id from Cart c where c.offerId = :offerId")
    Integer getCartIdByOfferId(@Param("offerId") int offerId);

}
