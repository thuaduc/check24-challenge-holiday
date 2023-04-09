package com.company.holidaybackend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@Getter @Setter @NoArgsConstructor
public class Hotel {

    @Id
    private int id;

    private String name;

    private short stars;

    public Hotel(int id, String name, short stars) {
        this.id = id;
        this.name = name;
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Hotel " + this.name + " with id " + this.id + " has " + this.stars + " stars.";
    }
}
