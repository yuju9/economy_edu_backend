package com.example.demo.ai.service;

import com.example.demo.ai.service.AiQuizService;
import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeeklyQuizScheduler {

    private final AiQuizService aiQuizService;

    //@Scheduled(cron  = "0 0/30 9-12 * * *") // 9시~12시 사이에 30분마다 실행
    public void generateWeeklyQuiz() {
        for (int level = 1; level <= 5; level++) {
            aiQuizService.generateQuizByLevel(level);
        }
    }

}
