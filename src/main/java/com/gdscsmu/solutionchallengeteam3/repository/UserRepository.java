package com.gdscsmu.solutionchallengeteam3.repository;

import com.gdscsmu.solutionchallengeteam3.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
