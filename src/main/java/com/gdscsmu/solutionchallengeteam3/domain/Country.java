package com.gdscsmu.solutionchallengeteam3.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long id;

    @Column
    private String countryName;

    @Column
    private int count;


    public Country(String countryName, int count) {
        this.countryName = countryName;
        this.count = count;
    }

    public void countUp() {
        this.count++;
    }
}
