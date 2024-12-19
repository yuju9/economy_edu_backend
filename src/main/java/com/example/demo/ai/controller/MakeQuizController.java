package com.example.demo.ai.controller;

import com.example.demo.ai.service.AiQuizService;
import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.model.QuizHistoryReq;
import com.example.demo.quiz.model.QuizRes;
import com.example.demo.quiz.model.QuizResultReq;
import com.example.demo.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MakeQuizController {


    private final AiQuizService aiQuizService;
    private final QuizService quizService;

//
//    @GetMapping("/generate-quiz")
//    public String generateQuiz() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        String userId = authentication.getName();
//        // 사용자 레벨을 조회
//        int userLevel = quizService.getUserLevel(userId);
//        return aiQuizService.callFastApi(userLevel);
//    }


}


