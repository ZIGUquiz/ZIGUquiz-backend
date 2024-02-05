package com.gdscsmu.solutionchallengeteam3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String nationality;

    @OneToMany(mappedBy = "user") // User와 Board 간의 일대다 관계를 나타냅니다. 'user'는 Board 클래스의 User 필드를 참조합니다.
    private List<Board> boards = new ArrayList<>();
}
