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
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CountryController {
    private final CountryService countryService;

    @ResponseBody
    @PostMapping("/set-country")
    public ResponseEntity<?> updateNationality(@CookieValue(value = "token") String token,@RequestBody CountryDto messageBody) throws IOException {
        Map userAttributes = JwtParser.parseUser(token);
        UserDto userDto = UserDto.of(userAttributes);
        String inputNationality = messageBody.getNationality();
        countryService.countrySet(userDto.getEmail(), inputNationality);


        return ResponseEntity.ok().body("success");
    }
}
