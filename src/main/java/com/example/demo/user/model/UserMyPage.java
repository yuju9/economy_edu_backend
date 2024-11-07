package com.example.demo.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMyPage {

    private String userId;
    private String userNickname;
    private String userName;
    private String userEmail;
    private String userBirthday;

    private int userLevel;

    private List<QuizResultInfo> problemHistory;

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

