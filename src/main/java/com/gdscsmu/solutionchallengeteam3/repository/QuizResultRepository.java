package com.gdscsmu.solutionchallengeteam3.repository;

import com.gdscsmu.solutionchallengeteam3.domain.QuizResult;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuizResultRepository {

    private final EntityManager em;

    public void save(QuizResult quizResult) {
        em.persist(quizResult);
    }

    public List<QuizResult> findByEmail(String email) {
        return em.createQuery("select m from QuizResult m where m.email = :email", QuizResult.class)
                .setParameter("email", email)
                .getResultList();
    }
}
