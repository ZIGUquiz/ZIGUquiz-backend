package com.gdscsmu.solutionchallengeteam3.service;

import com.gdscsmu.solutionchallengeteam3.domain.Country;
import com.gdscsmu.solutionchallengeteam3.domain.QuizResult;
import com.gdscsmu.solutionchallengeteam3.domain.dto.UserDto;
import com.gdscsmu.solutionchallengeteam3.domain.user.User;

import com.gdscsmu.solutionchallengeteam3.repository.CountryRepository;
import com.gdscsmu.solutionchallengeteam3.repository.QuizResultRepository;
import com.gdscsmu.solutionchallengeteam3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryService {
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final QuizResultRepository quizResultRepository;

    public User findUser(Map attributes) {
        return userRepository.findByEmail(attributes.get("email").toString());
    }

    //User가 나라설정창에서 나라 입력했을 때
    public void countrySet(Map userAttributes, String inputNationality) {
        User user = findUser(userAttributes);
        user.setNationality(inputNationality);
        userRepository.save(user);
    }

    public void quizDone(Map userAttributes, int score) {
        //퀴즈결과 저장
        User user = findUser(userAttributes);
        String email = user.getEmail();
        QuizResult quizResult = QuizResult.createQuizResult(email, score);


        //나라순에 저장
        String nationality = user.getNationality();
        Country country = countryRepository.findByCountryName(nationality);
        if (country == null) {
            country = new Country(nationality, 0);
        }


        List<QuizResult> findQR = quizResultRepository.findByEmail(email);
        if (findQR.isEmpty()) //참여한 적 있을경우 카운트업
            country.countUp();

        quizResultRepository.save(quizResult);
        countryRepository.save(country);
    }

    public Map<String, Integer> getRank() {
        int rankNum = 3;

        List<Country> countries = countryRepository.findAll();
        countries.sort(Comparator.comparingInt(Country::getCount).reversed());

        if(countries.size() < rankNum)
            rankNum = countries.size();

        countries = countries.subList(0,rankNum);

        Map<String, Integer> map = countries.stream().collect(
                Collectors.toMap(Country::getCountryName, Country::getCount));

        return map;
    }
}
