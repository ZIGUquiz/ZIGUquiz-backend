package com.gdscsmu.solutionchallengeteam3.domain.auth;

import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;


    public SessionUser(User user) {
        this.name = name;
        this.email = email;
    }

}
