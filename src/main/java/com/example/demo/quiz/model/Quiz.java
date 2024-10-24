package com.example.demo.quiz.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizSeq;

    @Column(nullable = false)
    private int quizLevel;

    @Column(nullable = false)
    private String quizQuestion;

    @Column(nullable = false)
    private String quizAnswer;

    @ManyToOne
    @JoinColumn(name = "quiz_category_seq", referencedColumnName = "quizCategorySeq")
    private QuizCategory quizCategory;


}
