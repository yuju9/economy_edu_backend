package com.example.demo.ai.controller;

import com.example.demo.ai.service.QuizService;
import com.example.demo.quiz.model.Quiz;
import com.example.demo.user.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AiQuizController {


    private final QuizService quizService;

    public AiQuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    // 사용자 레벨에 맞는 문제 생성 API
    @GetMapping ("/quiz")
    public List<Quiz> getQuizByLevel() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userId = userDetails.getUsername();
        // 사용자 레벨을 조회
        int userLevel = quizService.getUserLevel(userId);

        return quizService.getQuizzesByLevel(userLevel, userId);

    }

}


