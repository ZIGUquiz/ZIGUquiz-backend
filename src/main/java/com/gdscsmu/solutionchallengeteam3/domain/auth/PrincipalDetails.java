package com.gdscsmu.solutionchallengeteam3.domain.auth;

import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//UserDetails 인터페이스를 구현하게 되면 Spring Security에서 구현한 클래스를 사용자 정보로 인식하고 인증 작업을 한다
//출처: https://to-dy.tistory.com/86 [todyDev:티스토리]
public class PrincipalDetails implements OAuth2User {

    private User user;
    private Map<String, Object> attributes;

    //OAuth2User : OAuth2 로그인 시 사용
    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    /**
     * UserDetails 구현
     * 해당 유저의 권한목록 리턴
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole().toString();
            }
        });
        return collect;
    }

    /**
     * UserDetails 구현
     * 비밀번호를 리턴
     */
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    /**
//     * UserDetails 구현
//     * PK값을 반환해준다
//     */
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    /**
//     * UserDetails 구현
//     * 계정 만료 여부
//     *  true : 만료안됨
//     *  false : 만료됨
//     */
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    /**
//     * UserDetails 구현
//     * 계정 잠김 여부
//     *  true : 잠기지 않음
//     *  false : 잠김
//     */
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    /**
//     * UserDetails 구현
//     * 계정 비밀번호 만료 여부
//     *  true : 만료 안됨
//     *  false : 만료됨
//     */
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    /**
//     * UserDetails 구현
//     * 계정 활성화 여부
//     *  true : 활성화됨
//     *  false : 활성화 안됨
//     */
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

    /**
     * OAuth2User 구현
     * @return
     */
    @Override
    public String getName() {
        String sub = attributes.get("sub").toString();
        return sub;
    }

    /**
     * OAuth2User 구현
     * @return
     */
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public String  getProviderId() {
        return user.getProviderId();
    }
}
