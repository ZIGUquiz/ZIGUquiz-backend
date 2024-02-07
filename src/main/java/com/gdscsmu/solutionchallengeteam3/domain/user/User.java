package com.gdscsmu.solutionchallengeteam3.domain.user;

import com.gdscsmu.solutionchallengeteam3.domain.Board;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

//    구글 소셜로그인
    @Column
    private String provider;
    @Column
    private String providerId;

    @OneToMany(mappedBy = "user") // User와 Board 간의 일대다 관계를 나타냅니다. 'user'는 Board 클래스의 User 필드를 참조합니다.
    private List<Board> boards = new ArrayList<>();

    @Builder
    public User(String name, String email, String provider, String providerId, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public User update(String name) {
        this.name = name;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
