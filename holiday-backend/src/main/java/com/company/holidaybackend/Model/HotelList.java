package com.company.holidaybackend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class HotelList {

    @Id
    private int id;
    private String name;
    private int stars;
    private long count;
    private Double min_price;

    public HotelList(int id, String name, int stars, long count, Double min_price) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.stars = stars;
        this.min_price = min_price;
    }
}
