package com.company.holidaybackend.Model;

import jakarta.annotation.*;
import jakarta.persistence.*;


@Entity
@Table
public class Hotel {

    @Id
    @Nonnull
    private int id;

    private String name;

    private short stars;

    public Hotel(int id, String name, short stars) {
        this.id = id;
        this.name = name;
        this.stars = stars;
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

    public short getStars() {
        return stars;
    }

    public void setStars(short stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Hotel " + this.name + " with id " + this.id + " has " + this.stars + " stars.";
    }
}
