package com.example.demo.quiz.controller;

import com.example.demo.quiz.model.QuizHistoryReq;
import com.example.demo.quiz.model.QuizRes;
import com.example.demo.quiz.service.QuizService;
import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.model.QuizResultReq;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {


    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    // 사용자 레벨에 맞는 문제 생성 API
    @GetMapping ("/quiz")
    public ResponseEntity<QuizRes> getQuizByLevel(@RequestParam("category") String quizCategory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = authentication.getName();
        // 사용자 레벨을 조회
        int userLevel = quizService.getUserLevel(userId);
        int userSeq = quizService.getUserSeq(userId);
        int userExp = quizService.getUserExp(userId);

        List<Quiz> Quizzes = quizService.getQuizzesByLevel(userLevel, userSeq, quizCategory);

        QuizRes quizRes = new QuizRes();
        quizRes.setQuiz(Quizzes);
        quizRes.setUserLevel(userLevel);
        quizRes.setUserExp(userExp);

        return ResponseEntity.ok(quizRes);

    }

    //문제 푼거 저장
    //경험치 올리기
    @PostMapping ("/quiz")
    public void endQuiz(@RequestBody QuizResultReq quizResultReq) {
        System.out.println(quizResultReq);
        // 사용자 레벨을 조회
        quizService.updateExp(quizResultReq.getExp(), quizResultReq.getLevel());

    }

    @PostMapping ("/history")
    public void historyQuiz(@RequestBody QuizHistoryReq quizHistoryReq) {
        System.out.println(quizHistoryReq);
        // 사용자 레벨을 조회

    }

}


