package com.example.demo.quiz.service;

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

import java.util.ArrayList;
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

    public int getUserSeq(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return Math.toIntExact(user.getUserSeq()); // 사용자 ID로 레벨을 조회
    }

    public int getUserExp(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return Math.toIntExact(user.getUserExp()); // 사용자 ID로 레벨을 조회
    }

    @Transactional
    public List<Quiz> getQuizzesByLevel(int userLevel, int userSeq, String quizCategory) {
        int[][] quizCounts = {
                {},
                {7, 2, 1, 0, 0},
                {3, 5, 2, 0, 0},
                {1, 2, 4, 3, 0},
                {0, 2, 4, 3, 1},
                {0, 0, 2, 4, 4}
        };

        if (userLevel < 1 || userLevel > 5) {
            throw new IllegalArgumentException("사용자 레벨 없음");
        }

        int[] levelCounts = quizCounts[userLevel]; // 현재 사용자 레벨에 해당하는 퀴즈 개수
        List<Quiz> quizzes = new ArrayList<>();


        for (int level = 1; level <= levelCounts.length; level++) {
            int count = levelCounts[level - 1];
            if (count > 0) {
                quizzes.addAll(quizRepository.findQuizByLevel(level, userSeq, count, quizCategory));
            }

        }
        return quizzes;
    }

    @Transactional
    public void updateExp(int exp, int level) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userId = userDetails.getUsername();

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        int newExp = Math.max(exp, 0);


        //        if (newExp > 100) {
//            newLevel += 1;
//            newExp -= 100;
//            if (newLevel > 5) {
//                newLevel = 5;
//                newExp = 100;
//            }
//        }


        user.setUserExp(newExp);
        user.setUserLevel(level);

        userRepository.save(user);

    }
}
