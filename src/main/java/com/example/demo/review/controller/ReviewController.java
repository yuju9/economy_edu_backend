//package com.example.demo.review.controller;
//
//import com.example.demo.review.model.Review;
//import com.example.demo.review.service.ReviewService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api")
//public class ReviewController {
//
//    private final ReviewService reviewService;
//
//    @GetMapping("/review")
//    @Tag(name = "오답노트 api")
//    public ResponseEntity<Review> review() {
//        Review review = reviewService.review();
//        return ResponseEntity.ok(review);
//    }
//}
