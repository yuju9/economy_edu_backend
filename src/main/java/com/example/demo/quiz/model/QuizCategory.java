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
public class QuizCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizCategorySeq;


    @Column(nullable = false, unique = true)
    private String quizCategoryName;


}
