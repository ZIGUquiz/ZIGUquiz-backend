package com.gdscsmu.solutionchallengeteam3.domain.dto.response;

import com.gdscsmu.solutionchallengeteam3.domain.dto.UserDto;
import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResponse {
    private String token;
    private boolean isNewMember;

    // 생성자
    public LoginResponse(String token, boolean isNewMember) {
        this.token = token;
        this.isNewMember = isNewMember;
    }
}
