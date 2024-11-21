package com.example.demo.ai.service;

import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.repository.QuizRepository;
import com.example.demo.user.CustomUserDetails;
import com.example.demo.user.model.User;
import com.example.demo.user.model.UserMyPage;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    public int getUserLevel(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getUserLevel(); // 사용자 ID로 레벨을 조회
    }

    @Transactional
    public List<Quiz> getQuizzesByLevel(int userLevel, String userId) {
        List<Quiz> quizzes = quizRepository.findQuizzesByLevel(userLevel, userId);
        // 필요한 문제 개수만큼 자르기
        return quizzes.subList(0, Math.min(20, quizzes.size()));
    }

}
