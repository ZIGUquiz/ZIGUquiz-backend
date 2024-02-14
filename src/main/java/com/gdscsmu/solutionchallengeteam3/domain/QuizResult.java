package com.gdscsmu.solutionchallengeteam3.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizresult_id")
    private Long id;

    @Column
    private String email;

    @Column
    private int correct;

    public static QuizResult createQuizResult(String email, int correct) {
        QuizResult quizResult = new QuizResult();
        quizResult.setEmail(email);
        quizResult.setCorrect(correct);
        return quizResult;
    }
}
