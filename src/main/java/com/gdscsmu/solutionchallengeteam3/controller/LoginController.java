package com.gdscsmu.solutionchallengeteam3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdscsmu.solutionchallengeteam3.domain.dto.JwtDto;
import com.gdscsmu.solutionchallengeteam3.domain.dto.UserDto;
import com.gdscsmu.solutionchallengeteam3.domain.dto.response.LoginResponse;
import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import com.gdscsmu.solutionchallengeteam3.service.LoginService;
import com.gdscsmu.solutionchallengeteam3.util.JwtParser;
import com.gdscsmu.solutionchallengeteam3.util.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<?> jwtParse(@RequestBody HashMap<String, String> requestMap) throws IOException {


        //request로 넘어온 정보
        ResponseEntity<String> userInfo = requestUserInfo(requestMap.get("access_token"));
        String userInfoBody = userInfo.getBody();


        ObjectMapper mapper = new ObjectMapper();
        Map userAttributes = mapper.readValue(userInfoBody, Map.class);

//        user 존재 판별
        loginService.loginProcess(userAttributes);

        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String token = jwtTokenProvider.createToken(userAttributes.get("email").toString());

        return ResponseEntity.ok().body(token);
    }

    public ResponseEntity<String> requestUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        String GOOGLE_USERINFO_REQUEST_URL="https://www.googleapis.com/oauth2/v1/userinfo";

        //header에 accessToken을 담는다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+ accessToken);

        //HttpEntity를 하나 생성해 헤더를 담아서 restTemplate으로 구글과 통신하게 된다.
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(headers);
        ResponseEntity<String> response= restTemplate.exchange(GOOGLE_USERINFO_REQUEST_URL, HttpMethod.GET,request,String.class);
        return response;
    }
}

