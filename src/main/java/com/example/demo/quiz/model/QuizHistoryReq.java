package com.example.demo.quiz.model;

import lombok.Data;

@Data
public class QuizHistoryReq {

    public int problemId;
    public String title;
    public String result;
}

