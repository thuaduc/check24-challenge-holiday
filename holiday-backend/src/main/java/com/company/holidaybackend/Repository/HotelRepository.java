package com.company.holidaybackend.Repository;

import com.company.holidaybackend.Model.Hotel;
import com.company.holidaybackend.Model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
