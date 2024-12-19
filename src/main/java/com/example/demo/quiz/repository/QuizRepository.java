package com.example.demo.quiz.repository;

import com.example.demo.quiz.model.Quiz;
import com.example.demo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.hibernate.sql.ast.Clause.FROM;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {


    @Query(value = """
           SELECT * FROM quiz q
           WHERE q.quiz_level = :quizLevel
           AND q.quiz_seq NOT IN (
               SELECT qr.quiz_seq
               FROM quiz_result qr
               WHERE qr.user_seq = :userId
           )
           AND q.quiz_category = :quizCategory
           ORDER BY RAND()
           LIMIT :count
           """, nativeQuery = true)
    List<Quiz> findQuizByLevel(@Param("quizLevel") int quizLevel, @Param("userId") int userId, @Param("count") int count, @Param("quizCategory") String quizCategory);

//    @Query(value = """
//           SELECT * FROM quiz q
//           WHERE q.quiz_level = :quizLevel
//           ORDER BY RAND()
//           LIMIT :count
//           """, nativeQuery = true)
//    List<Quiz> findQuizzesByLevel(@Param("quizLevel") int quizLevel, @Param("count") int count);
}
