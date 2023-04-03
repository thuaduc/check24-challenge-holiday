package com.company.holidaybackend.Model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import static java.lang.Double.parseDouble;

@Entity
public class Hotel {

    @Id
    private int id;

    private String name;

    private double stars;

    public Hotel(Integer id, String name, Double stars) {
        this.id = id.intValue();
        this.name = name;
        this.stars = stars.doubleValue();
    }

    public Hotel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Hotel " + this.name + " with id " + this.id + " has " + this.stars + " stars.";
    }
}
