package com.example.demo.quiz.repository;

import com.example.demo.quiz.model.Quiz;
import com.example.demo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {


    @Query("""
           SELECT q FROM Quiz q\s
           WHERE q.quizLevel = :quizLevel\s
           AND q.quizSeq NOT IN (
               SELECT qr.quiz.quizSeq\s
               FROM QuizResult qr\s
               WHERE qr.user.userId = :userId
           )
           ORDER BY function('RAND')
           """)
    List<Quiz> findQuizzesByLevel(@Param("quizLevel") int quizLevel, @Param("userId") String userId);


}
