package com.gdscsmu.solutionchallengeteam3.config.auth;

import com.gdscsmu.solutionchallengeteam3.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService oAuth2UserService;
//    private final OAuth2SuccessHandler oAuth2SuccessHandler;
//    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    //권한 확인을 하지 않는 uri
    //https://mag1c.tistory.com/137
    private static final String[] PERMIT_ALL_PATTERNS = new String[] {
            "/",
            "/login",
            "/admin",
            "/session/user"
    };

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { // security를 적용하지 않을 리소스
        return web -> web.ignoring()
                // error endpoint를 열어줘야 함, favicon.ico 추가!
                .requestMatchers("/error", "/favicon.ico");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // rest api 설정
                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화 -> cookie를 사용하지 않으면 꺼도 된다. (cookie를 사용할 경우 httpOnly(XSS 방어), sameSite(CSRF 방어)로 방어해야 한다.)
                .cors(AbstractHttpConfigurer::disable) // cors 비활성화 -> 프론트와 연결 시 따로 설정 필요
                .httpBasic(AbstractHttpConfigurer::disable) // 기본 인증 로그인 비활성화
                .formLogin(AbstractHttpConfigurer::disable) // 기본 login form 비활성화
                .logout(AbstractHttpConfigurer::disable) // 기본 logout 비활성화
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용하지 않음

                // request 인증, 인가 설정
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(PERMIT_ALL_PATTERNS)   //인증없이 접근 가능
                        .permitAll()
//                        .hasAuthority("USER")
//                        .anyRequest().authenticated() //나머지 접근에 대해서는 인증된 사용자만 접근 가능하게 합니다.
                        .anyRequest().anonymous()
                )

                // oauth2 설정
                .oauth2Login(oauth -> oauth
                                .loginPage("/login")
                                // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                                // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
                                .userInfoEndpoint(c -> c.userService(oAuth2UserService))
                                .defaultSuccessUrl("/")        //로그인 성공시
                                .failureUrl("/auth/login")    //로그인 실패시
                        // 로그인 성공 시 핸들러
//                        .successHandler(oAuth2SuccessHandler)
                );

        // jwt 관련 설정
//                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).addFilterBefore(new TokenExceptionFilter(), tokenAuthenticationFilter.getClass()) // 토큰 예외 핸들링

        // 인증 예외 핸들링
//                .exceptionHandling((exceptions) -> exceptions.authenticationEntryPoint(new CustomAuthenticationEntryPoint()).accessDeniedHandler(new CustomAccessDeniedHandler()));

        return http.build();
    }
}
