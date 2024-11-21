package com.example.demo.ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuizAiResponseBody {
    private int quizLevel;
    private String category;
    private String question;
    private String options;
    private String answer;
    private String comment;
}
