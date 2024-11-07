package com.example.demo.user.controller;

import com.example.demo.user.CustomUserDetails;
import com.example.demo.user.model.*;
import com.example.demo.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyPageController {

    private final UserService userService;

    @GetMapping("/myPage")
    public ResponseEntity<UserMyPage> getUserMyPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 사용자 정보를 가져옵니다.
        User user = userService.getUserByUsername(username);

        UserMyPage userMyPage = new UserMyPage();
        userMyPage.setUserId(user.getUserId());
        userMyPage.setUserNickname(user.getUserNickname());
        userMyPage.setUserName(user.getUserName());
        userMyPage.setUserEmail(user.getUserEmail());
        userMyPage.setUserBirthday(user.getUserBirthday());
        userMyPage.setUserLevel(user.getUserLevel());

        List<UserMyPage.QuizResultInfo> quizResultInfos = user.getQuizResults().stream().map(result -> {
            UserMyPage.QuizResultInfo quizResultInfo = new UserMyPage.QuizResultInfo();
            quizResultInfo.setId(String.valueOf(result.getQuiz().getQuizSeq()));
            quizResultInfo.setTitle(result.getQuiz().getQuizQuestion());
            quizResultInfo.setResult(result.getQuizCorrectState().toString());
            quizResultInfo.setDate(result.getQuizCorrectDate().toString());
            return quizResultInfo;
        }).toList();

        userMyPage.setProblemHistory(quizResultInfos);

        return ResponseEntity.ok(userMyPage);
    }
}

