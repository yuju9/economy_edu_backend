package com.example.demo.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizRes {

    private int userLevel;
    private int userExp;

    private List<Quiz> Quiz;

    // Inner class to hold quiz question and result information
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuizResultInfo {
        private int id;
        private int quizLevel;
        private String category;
        private String question;
        private String options;
        private String answer;
        private String comment;
    }
}

