package com.gdscsmu.solutionchallengeteam3.service;

import com.gdscsmu.solutionchallengeteam3.domain.auth.PrincipalDetails;
import com.gdscsmu.solutionchallengeteam3.domain.dto.OAuthAttributes;
import com.gdscsmu.solutionchallengeteam3.domain.auth.SessionUser;
import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import com.gdscsmu.solutionchallengeteam3.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service @Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user));    // 세션에 저장
        httpSession.setMaxInactiveInterval(1800);   //세션 유효시간 : 1800초 = 30분

        return new PrincipalDetails(user, oAuth2User.getAttributes());
//        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName())) //사용자가 이미 존재한다면
                .orElseGet(() -> {
                    User newUser = attributes.toEntity(); // 새 사용자 생성
                    return userRepository.save(newUser); // 새 사용자 데이터베이스에 저장
                });

        return user;
//        return userRepository.save(user);
    }

    // CustomOAuth2UserService 내에서 사용자 로드 후 처리
//    private User saveOr3Update(OAuthAttributes attributes) {
//        User user = userRepository.findByEmail(attributes.getEmail())
//                .map(entity -> entity.update(attributes.getName()))
//                .orElseGet(() -> {
//                    User newUser = attributes.toEntity(); // 새 사용자 생성
//                    return userRepository.save(newUser); // 새 사용자 데이터베이스에 저장
//                });
//
//        if (user.isNew()) { // User 엔티티에 isNew() 메소드를 추가하여 새 사용자인지 확인
//            // 새 사용자를 위한 추가 정보 입력 페이지 URL로 리디렉션 처리 로직
//            // 예: 리디렉션 URL을 세션에 저장하거나, 프론트엔드에 신호 전송
//        }
//
//        return user;
//    }

}
