package com.gdscsmu.solutionchallengeteam3.domain;

import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long count;
}
