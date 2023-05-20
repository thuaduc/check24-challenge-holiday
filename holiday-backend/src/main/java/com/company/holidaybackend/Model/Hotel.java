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
public class Hotel {

    @Id
    private int id;

    private String name;

    private int stars;

    public Hotel(int id, String name, int stars) {
        this.id = id;
        this.name = name;
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Hotel " + this.name + " with id " + this.id + " has " + this.stars + " stars.";
    }
}
