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
                .allowedOrigins("*")    //나중에 배포 시 수정할것!!!!!!!
                .allowedMethods("*");
    }
}
