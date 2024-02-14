package com.gdscsmu.solutionchallengeteam3.repository;

import com.gdscsmu.solutionchallengeteam3.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCountryName(String countryName);
}