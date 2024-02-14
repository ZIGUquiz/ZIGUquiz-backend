package com.gdscsmu.solutionchallengeteam3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdscsmu.solutionchallengeteam3.domain.dto.JwtDto;
import com.gdscsmu.solutionchallengeteam3.domain.dto.UserDto;
import com.gdscsmu.solutionchallengeteam3.domain.dto.response.LoginResponse;
import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import com.gdscsmu.solutionchallengeteam3.service.LoginService;
import com.gdscsmu.solutionchallengeteam3.util.JwtParser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @ResponseBody
    @PostMapping("/auth/login")
    public ResponseEntity<?> jwtParse(@RequestBody String messageBody) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JwtDto token = objectMapper.readValue(messageBody, JwtDto.class);
        Map userAttributes = JwtParser.parseUser(token.toString());

        //user 존재 판별
        LoginResponse loginResult = loginService.loginProcess(userAttributes);


        return ResponseEntity.ok().body(loginResult);
    }
}
