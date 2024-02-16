package com.gdscsmu.solutionchallengeteam3.service;


import com.gdscsmu.solutionchallengeteam3.domain.dto.UserDto;
import com.gdscsmu.solutionchallengeteam3.domain.dto.response.LoginResponse;
import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import com.gdscsmu.solutionchallengeteam3.repository.UserRepository;
import com.gdscsmu.solutionchallengeteam3.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public LoginResponse loginProcess (Map userAttributes) {

        UserDto userDto = UserDto.of(userAttributes);
        User user = userRepository.findByEmail(userDto.getEmail());

        // 사용자 찾기 또는 새로운 사용자 생성
        boolean isNewMember = false;

        if (user == null) {
            // 새 사용자 생성 및 저장 로직 (가정)
            user = User.fromUserDto(userDto);
            // 다른 필요한 사용자 정보 설정
            userRepository.save(user);
            isNewMember = true;
        }
//        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
//        String token = jwtTokenProvider.createToken(userAttributes.get("name").toString(), userAttributes.get("email").toString());

        return new LoginResponse(user, isNewMember);
    }

}
