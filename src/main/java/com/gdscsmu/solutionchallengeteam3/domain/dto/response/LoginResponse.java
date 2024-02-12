package com.gdscsmu.solutionchallengeteam3.domain.dto.response;

import com.gdscsmu.solutionchallengeteam3.domain.dto.UserDto;
import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResponse {
    private UserDto user;
    private boolean isNewMember;

    // 생성자
    public LoginResponse(User user, boolean isNewMember) {
        this.user = UserDto.fromUser(user);
        this.isNewMember = isNewMember;
    }
}
