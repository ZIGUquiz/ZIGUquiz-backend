package com.gdscsmu.solutionchallengeteam3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SolutionChallengeTeam3Application {

	public static void main(String[] args) {
		SpringApplication.run(SolutionChallengeTeam3Application.class, args);
	}

}
