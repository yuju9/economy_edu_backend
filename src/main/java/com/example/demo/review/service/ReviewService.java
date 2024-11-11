//package com.example.demo.review.service;
//
//import com.example.demo.quiz.model.QuizCorrectState;
//import com.example.demo.quiz.model.QuizResult;
//import com.example.demo.quiz.repository.QuizResultRepository;
//import com.example.demo.review.model.Review;
//import com.example.demo.user.CustomUserDetails;
//import com.example.demo.user.model.User;
//import com.example.demo.user.model.UserMyPage;
//import com.example.demo.user.model.UserState;
//import com.example.demo.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class ReviewService {
//
//    private final UserRepository userRepository;
//
//    private final QuizResultRepository quizResultRepository;
//
//
//    @Transactional
//    public Review review() {
//        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        String currentUserId = userDetails.getUsername();
//        User user = userRepository.findByUserId(currentUserId)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        List<QuizResult> incorrectQuizResults = quizResultRepository.findByUserAndQuizCorrectState(user, QuizCorrectState.INCORRECT);
//
//        List<Review.QuizResultInfo> quizResultInfos = incorrectQuizResults.stream()
//                .map(quizResult -> new Review.QuizResultInfo(
//                        String.valueOf(quizResult.getQuiz().getQuizSeq()),
//                        quizResult.getQuiz().getQuizQuestion(),
//                        quizResult.getQuizCorrectDate().toString(),
//                        quizResult.getQuizCorrectState().toString()
//                ))
//                .collect(Collectors.toList());
//
//        return new Review(quizResultInfos);
//    }
//
//}
