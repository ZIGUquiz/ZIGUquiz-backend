package com.gdscsmu.solutionchallengeteam3.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private static final String secretKey = "imsiSecretKEyimsiSecretKEyimsiSecretKEyimsiSecretKEyimsiSecretKEyimsiSecretKEyimsiSecretKEyimsiSecretKEy";

    // 토큰 유효시간 30분
    private long tokenValidTime = 30 * 60 * 1000L;


    // JWT 토큰 생성
    public String createToken(String email) {
        Claims claims = Jwts.claims().setSubject("accessToken"); // JWT payload 에 저장되는 정보단위
        claims.put("email", email); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
//                .setIssuedAt(now) // 토큰 발행 시간 정보
//                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }
}
