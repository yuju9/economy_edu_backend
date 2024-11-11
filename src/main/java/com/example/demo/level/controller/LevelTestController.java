package com.example.demo.level.controller;

import com.example.demo.level.model.LevelTestResultReq;
import com.example.demo.level.service.LevelTestService;
import com.example.demo.user.CustomUserDetails;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LevelTestController {

    private final LevelTestService levelTestService;

    @PutMapping("/levelTest")
    @Tag(name = "레벨테스트 api")
    public ResponseEntity<String> getLevelTestResult(@RequestBody LevelTestResultReq levelTestResultReq) {
        int userLevel = levelTestResultReq.getLevel();
        levelTestService.updateUserLevel(userLevel);
        
        return ResponseEntity.ok("LevelTest Finished");
    }
}
