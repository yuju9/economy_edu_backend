package com.example.demo.quiz.controller;

import com.example.demo.level.model.LevelTestResultReq;
import com.example.demo.level.service.LevelTestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final LevelTestService levelTestService;

    @PutMapping("/review")
    @Tag(name = "오답노트 api")
    public ResponseEntity<String> getLevelTestResult(@RequestBody LevelTestResultReq levelTestResultReq) {

        return ResponseEntity.ok("LevelTest Finished");
    }
}
