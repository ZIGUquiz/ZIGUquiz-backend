package com.gdscsmu.solutionchallengeteam3.domain.user;

import com.gdscsmu.solutionchallengeteam3.domain.dto.UserDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String nationality;
    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

//    구글 소셜로그인
    @Column
    private String provider;
    @Column
    private String providerId;

    public static User fromUserDto(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .picture(userDto.getPicture())
                .role(userDto.getRole())
                .provider(userDto.getProvider())
                .providerId(userDto.getProviderId())
                .build();
    }

    @Builder
    public User(String name, String email, String provider,String picture, String providerId, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public User setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
