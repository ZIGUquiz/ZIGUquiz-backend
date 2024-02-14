package com.gdscsmu.solutionchallengeteam3.service;

import com.gdscsmu.solutionchallengeteam3.domain.Country;
import com.gdscsmu.solutionchallengeteam3.domain.QuizResult;
import com.gdscsmu.solutionchallengeteam3.domain.dto.UserDto;
import com.gdscsmu.solutionchallengeteam3.domain.user.User;

import com.gdscsmu.solutionchallengeteam3.repository.CountryRepository;
import com.gdscsmu.solutionchallengeteam3.repository.QuizResultRepository;
import com.gdscsmu.solutionchallengeteam3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryService {
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final QuizResultRepository quizResultRepository;

    //User가 나라설정창에서 나라 입력했을 때
    public void countrySet(String email, String inputNationality) {
        User user = userRepository.findByEmail(email);
        user.setNationality(inputNationality);
        userRepository.save(user);
    }

    public void quizDone(UserDto userDto, int score) {
        //퀴즈결과 저장
        String email = userDto.getEmail();
        QuizResult quizResult = QuizResult.createQuizResult(email, score);
        quizResultRepository.save(quizResult);

        //나라순에 저장
        String nationality = userDto.getNationality();
        Country country = countryRepository.findByCountryName(nationality);
        if (country == null) {
            country = new Country(nationality, 1);
        }

        //참여한 적 없을경우 카운트없
        if (quizResultRepository.findByEmail(email) == null)
            country.countUp();

        countryRepository.save(country);
    }
}
