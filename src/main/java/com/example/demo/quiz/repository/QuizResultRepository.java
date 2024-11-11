package com.example.demo.quiz.repository;

import com.example.demo.quiz.model.QuizCorrectState;
import com.example.demo.quiz.model.QuizResult;
import com.example.demo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    List<QuizResult> findByUserAndQuizCorrectState(User user, QuizCorrectState quizCorrectState);
}
