package com.example.demo.ai.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiQuizService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String FAST_API_URL = "http://localhost:8000/generate-quiz/";

    public String callFastApi(int level) {
        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 바디 생성
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("level", level);
        requestBody.put("model_type", "gpt");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // FastAPI 호출
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    FAST_API_URL,
                    HttpMethod.POST,
                    request,
                    String.class
            );
            return response.getBody(); // 응답 반환
        } catch (Exception e) {
            e.printStackTrace();
            return "FastAPI 호출 실패: " + e.getMessage();
        }
    }
}
