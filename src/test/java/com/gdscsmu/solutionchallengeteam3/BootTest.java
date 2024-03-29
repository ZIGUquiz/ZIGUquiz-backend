package com.gdscsmu.solutionchallengeteam3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdscsmu.solutionchallengeteam3.domain.Country;
import com.gdscsmu.solutionchallengeteam3.domain.dto.UserDto;
import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import com.gdscsmu.solutionchallengeteam3.repository.CountryRepository;
import com.gdscsmu.solutionchallengeteam3.repository.QuizResultRepository;
import com.gdscsmu.solutionchallengeteam3.repository.UserRepository;
import com.gdscsmu.solutionchallengeteam3.service.CountryService;
import com.gdscsmu.solutionchallengeteam3.util.JwtParser;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;


@SpringBootTest
public class BootTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private QuizResultRepository quizResultRepository;

    String data = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImVkODA2ZjE4NDJiNTg4MDU0YjE4YjY2OWRkMWEwOWE0ZjM2N2FmYzQiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI3NTY2NDY4NDM2NDktZ2c5bjliN2Z2dHR0azdwZzZzZDIwbWNyaWFkbW4yYnAuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI3NTY2NDY4NDM2NDktZ2c5bjliN2Z2dHR0azdwZzZzZDIwbWNyaWFkbW4yYnAuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDUwMzIxOTA1MjY1MzU0MTQ2ODYiLCJlbWFpbCI6Inl1bmJpbjA5MjVAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5iZiI6MTcwNzY1ODI4MSwibmFtZSI6IuyhsOycpOu5iCIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NJMVpObTZmbHJqNHN4ajlJYm81bEtPeUpXbVIxblFyc1F1MEd0eUo0U25mZz1zOTYtYyIsImdpdmVuX25hbWUiOiLsnKTruYgiLCJmYW1pbHlfbmFtZSI6IuyhsCIsImxvY2FsZSI6ImtvIiwiaWF0IjoxNzA3NjU4NTgxLCJleHAiOjE3MDc2NjIxODEsImp0aSI6IjI1ZDQ5Y2Y5OGE2ZDFlOWZmZWM3MTY4ZmU1OTM3MTkyN2MxZDM3MTIifQ.bXaEGYT92ocZwKjpcmuwwAbPUDXkUU4cWOJDqKTrg3pn5DOa4cmWhG_JeqrLl5y2FAEWi_3sCAulbIh3YUeSm5wqB6nQ7jKIOzcwNmVLtZqELjc7JHYcR7wbsOJJLsM0jUESFhN7fwTII4k28n4VL5w9QFFlP0UgSh8PgsE-0yNHSa9UNGTVgWGd0idPzWcoHULOOCtXlWlAEFDohW3oUZxhiGveUGfWFztfZJgU4Rox-oPKJeVBSPREQXpXmU-WSXlfTMthU-pyrtUkyd67hmR0LH5Ts06BwRcWzM8N0_Cw_ycO9IPQkWIMXdWCheMpllXEjMjO5YQ_8drCf_kx2A";

    @Test
    public void 디코드테스트() throws JsonProcessingException {

        // 토큰을 각 섹션(Header, Payload, Signature)으로 분할
        String[] chunks = data.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();


        String payload = new String(decoder.decode(chunks[1]));

        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = mapper.readValue(payload, Map.class);

        UserDto userDto = UserDto.of(returnMap);

        System.out.println("returnMap = " + returnMap.get("email"));
        User user = userRepository.findByEmail(userDto.getEmail());

        // 사용자 찾기 또는 새로운 사용자 생성
        boolean isNewMember = false;

        if (user == null) {
            // 새 사용자 생성 및 저장 로직 (가정)
            user = User.fromUserDto(userDto);
            // 다른 필요한 사용자 정보 설정
            userRepository.save(user);
            isNewMember = true;
        }

        // 사용자 객체와 isNewMember 값을 포함한 결과 객체 반환 (가정)
        // 여기서는 단순 출력으로 대체
        System.out.printf("User: %s, isNewMember: %b%n", user.getEmail(), isNewMember);
    }

    @Test
    public void 나라업데이트() throws JsonProcessingException {
        String[] chunks = data.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();


        String payload = new String(decoder.decode(chunks[1]));

        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = mapper.readValue(payload, Map.class);

        UserDto userDto = UserDto.of(returnMap);

        String inputNationality = "japan";
        //service
        User user = userRepository.findByEmail(userDto.getEmail());
        user.setNationality(inputNationality);
        userRepository.save(user);

        System.out.printf("User: %s", user);
    }

    @Test
    public void 나라랭킹() {
        //set
        String[] name = {"aa", "bb", "cc","dd","ee","ff","gg","hh","ii","jj"};
        Integer[] num = {1,4,25,6,9,5,12,6,7,8};
        for (int i = 0; i < 10; i++) {
            Country country = new Country(name[i], num[i]);
            countryRepository.save(country);
        }
        //test
        List<Country> countries = countryRepository.findAll();
        countries.sort(Comparator.comparingInt(Country::getCount).reversed());

        //자르기
        int rankNum = 3;
        if(countries.size() < 3)
            rankNum = countries.size();

        countries = countries.subList(0,rankNum);

        Map<String, Integer> map = countries.stream().collect(
                Collectors.toMap(Country::getCountryName, Country::getCount));

        System.out.println(map);
    }
}
