package com.gdscsmu.solutionchallengeteam3.domain.dto;

import com.gdscsmu.solutionchallengeteam3.domain.user.Role;
import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String provider;
    private String providerId;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String provider, String providerId) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        return ofGoogle(registrationId, userNameAttributeName, attributes);
        //  OAuth2User에서 반환한 정보를  Map에서 OAuthAttributes로 반환하는 코드.
    }

    private static OAuthAttributes ofGoogle(String registrationId, String userNameAttributeName,Map<String, Object> attributes ){
        return OAuthAttributes.builder()
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .name((String) attributes.get("name"))
                .email((String)attributes.get("email"))
                .provider(registrationId)
                .providerId((String) attributes.get(userNameAttributeName))
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .provider(provider)
                .providerId(providerId)
                .role(Role.GUEST)
                .build();
        // user엔티티를 생성
        // OAuthAttributes가 user엔티티를 생성하는 시점은 처음 가입할때이므로 기본 권한을 게스트로 줘야함.
        // OAuthAttributes 클래스 생성이 끝났으면 같은 패키지에 세션클래스를 추가해야함
    }
}