package com.gdscsmu.solutionchallengeteam3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@EnableConfigurationProperties(value = AppProperties.class)
@TestPropertySource("classpath:application.yml")
class AppPropertiesTest {

//    @Autowired
//    private AppProperties appProperties;
//
//    @Test
//    @DisplayName("yml에서 키 가져와서 테스트")
//    void getAuth() {
//        AppProperties.Auth auth = appProperties.getAuth();
//
//        assertThat(auth.getTokenSecret()).isEqualTo("ZiGuSeCrET123ZiGuSeCrET123ZiGuSeCrET123ZiGuSeCrET123ZiGuSeCrET123");
//        assertThat(auth.getTokenExpirationMsec()).isEqualTo(864000000);
//    }
}
