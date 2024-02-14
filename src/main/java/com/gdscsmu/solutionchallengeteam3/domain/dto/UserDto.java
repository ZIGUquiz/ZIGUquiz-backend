package com.gdscsmu.solutionchallengeteam3.domain.dto;

import com.gdscsmu.solutionchallengeteam3.domain.user.Role;
import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    private String name;
    private String email;
    private String nationality;
    private String picture;
    private String provider;
    private String providerId;
    private Role role;



    public User toEntity(){
        User user = User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .nationality(nationality)
                .provider(provider)
                .providerId(providerId)
                .build();

        return user;
    }

    public static UserDto of(Map attributes) {
        return UserDto.builder()
                .name(attributes.get("name").toString())
                .email(attributes.get("email").toString())
                .picture(attributes.get("picture").toString())
                .providerId(attributes.get("sub").toString())
                .nationality("korea")
                .provider("google")
                .role(Role.USER)
                .build();
    }

    public static UserDto fromUser(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .picture(user.getPicture())
                .role(user.getRole())
                .nationality(user.getNationality())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .build();
    }

    @Builder
    public UserDto(String name, String email, String provider,String picture, String nationality, String providerId, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.nationality = nationality;
        this.provider = provider;
        this.providerId = providerId;
    }

}
