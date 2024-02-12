package com.gdscsmu.solutionchallengeteam3.repository;

import com.gdscsmu.solutionchallengeteam3.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
