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

    // 답안 선택지
    @Column(columnDefinition = "TEXT")
    private String quizOption;

    @Column(nullable = false)
    private String quizAnswer;

//    @Column(nullable = false)
    @Column
    private String quizCategory; //GPT, LLAMA

    @Column(nullable = false)
    private String quizComment;


//    @ManyToOne
//    @JoinColumn(name = "quiz_result_seq", referencedColumnName = "quizResultSeq")
//    private QuizResult quizResult;

}
