package com.gdscsmu.solutionchallengeteam3.service;

import com.gdscsmu.solutionchallengeteam3.domain.Country;
import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import com.gdscsmu.solutionchallengeteam3.repository.CountryRepository;

import com.gdscsmu.solutionchallengeteam3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryService {
    private final UserRepository userRepository;
//    private final CountryRepository countryRepository;

    //User가 나라설정창에서 나라 입력했을 때
    public void countrySet(String email, String inputNationality) {
        User user = userRepository.findByEmail(email);
        user.setNationality(inputNationality);
        userRepository.save(user);
    }
}
