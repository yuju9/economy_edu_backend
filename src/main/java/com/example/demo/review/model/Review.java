package com.example.demo.review.model;

import com.example.demo.user.model.UserMyPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {


    private List<QuizResultInfo> quizResultInfos;

    // Inner class to hold quiz question and result information
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuizResultInfo {
        private String id;
        private String title;
        private String date;
        private String result;
    }
}

