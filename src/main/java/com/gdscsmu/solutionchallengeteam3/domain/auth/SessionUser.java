package com.gdscsmu.solutionchallengeteam3.domain.auth;

import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String providerId;
//    private String nationality;


    public SessionUser(User user) {
        this.name = user.getName();;
        this.email = user.getEmail();
        this.providerId = user.getProviderId();
    }
}
