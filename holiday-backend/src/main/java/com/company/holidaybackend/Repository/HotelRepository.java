package com.company.holidaybackend.Repository;

import com.company.holidaybackend.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    @Query(value = "SELECT h from Hotel h WHERE h.stars >= 5 ORDER BY h.stars DESC LIMIT 20")
    List<Hotel> queryBestHotel();

}
