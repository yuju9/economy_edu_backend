package com.example.demo.quiz.model;


import com.example.demo.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizResultSeq;

    @Enumerated(EnumType.STRING)
    private QuizCorrectState quizCorrectState;

    @CreationTimestamp
    private Timestamp quizCorrectDate;

    @ManyToOne
    @JoinColumn(name = "user_seq", referencedColumnName = "userSeq")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_seq", referencedColumnName = "quizSeq")
    private Quiz quiz;


}
