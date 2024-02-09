package com.gdscsmu.solutionchallengeteam3;

import com.gdscsmu.solutionchallengeteam3.config.auth.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SolutionChallengeTeam3Application {

	public static void main(String[] args) {
		SpringApplication.run(SolutionChallengeTeam3Application.class, args);
	}

}
