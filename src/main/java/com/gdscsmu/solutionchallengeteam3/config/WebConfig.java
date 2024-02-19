package com.gdscsmu.solutionchallengeteam3.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins("http://localhost:3000", "http://35.208.142.216:3000", "http://ziguquiz.site", "https://ziguquiz.site")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3000);
    }
}
