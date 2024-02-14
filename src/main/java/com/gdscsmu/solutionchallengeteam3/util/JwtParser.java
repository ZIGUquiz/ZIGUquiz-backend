package com.gdscsmu.solutionchallengeteam3.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdscsmu.solutionchallengeteam3.domain.dto.JwtDto;

import java.util.Base64;
import java.util.Map;

public class JwtParser {
    public static Map parseUser(String jwt) throws JsonProcessingException {
        String[] chunks = jwt.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        //jwt 디코딩 코드
        String payload = new String(decoder.decode(chunks[1]));
        ObjectMapper mapper = new ObjectMapper();
        Map userAttributes = mapper.readValue(payload, Map.class);

        return userAttributes;
    }
}
