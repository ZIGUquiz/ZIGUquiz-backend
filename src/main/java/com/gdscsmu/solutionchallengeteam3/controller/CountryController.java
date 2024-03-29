package com.gdscsmu.solutionchallengeteam3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdscsmu.solutionchallengeteam3.domain.dto.CountryDto;
import com.gdscsmu.solutionchallengeteam3.domain.dto.JwtDto;
import com.gdscsmu.solutionchallengeteam3.domain.dto.UserDto;
import com.gdscsmu.solutionchallengeteam3.domain.dto.response.LoginResponse;
import com.gdscsmu.solutionchallengeteam3.service.CountryService;
import com.gdscsmu.solutionchallengeteam3.util.JwtParser;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CountryController {
    private final CountryService countryService;

    @ResponseBody
    @PostMapping("/api/user/set-country")
    public ResponseEntity<?> updateNationality(@CookieValue(value = "accessToken") String token,@RequestBody CountryDto messageBody) throws IOException {
        // ***** 쿠키로 유저찾기
        Map userAttributes = JwtParser.parseUser(token);
        // *****

        String inputNationality = messageBody.getNationality();
        countryService.countrySet(userAttributes, inputNationality);


        return ResponseEntity.ok().body("success");
    }

    @ResponseBody //cookie 받고 , 나라  +1 (예외처리)
    @PatchMapping("/api/quiz/update-rank")
    public ResponseEntity<?> updateRank(@CookieValue(value = "accessToken") String token, @RequestBody HashMap<String, String> requestMap) throws IOException {
        // ***** 쿠키로 유저찾기
        Map userAttributes = JwtParser.parseUser(token);
        // *****

        int score = Integer.parseInt(requestMap.get("score"));

        countryService.quizDone(userAttributes, score);


        return ResponseEntity.ok().body("success");
    }

    @ResponseBody //cookie 받고 , 나라  +1 (예외처리)
    @GetMapping("/api/quiz/rank")
    public ResponseEntity<?> getRank() {

        Map<String, Integer> rank = countryService.getRank();

        return ResponseEntity.ok().body(rank);
    }
}
