package com.example.demo.ai.service;

import com.example.demo.ai.model.QuizAiResponseBody;
import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.repository.QuizRepository;
import com.example.demo.user.model.User;
import com.example.demo.user.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AiQuizService {

    @Autowired
    private ObjectMapper objectMapper;

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final QuizRepository quizRepository;



    // 사용자 레벨을 기반으로 문제 생성 요청
    public String generateQuizByLevel(int level) {
        String url = "http://localhost:9001/api/generate-response";  // FastAPI URL

        // 레벨에 맞춘 문제 생성 요청
        String levelPrompt = "경제 상식 레벨" + level + "인 사람을 위한 경제 및 금융 상식 문제를 한국어로 내줘. 응답형식은 JSON으로 문제 난이도 'quizLevel(1~5레벨로 가정)', 'category', 'question', 'options', 'answer', 'comment'로 나눠서 반환해줘. category는 '개념', '응용', '경제사'로 나누고, option은 문제 선택지로 4개의 선택지가 있으면 될 것같아. comment에 는 문제에 대한 해설을 첨가해줘. 1문제만 예시로 줘. 형태는 꼭 {\"quizLevel\": \"2\", \"category\": \"question\": \"GDP는 무엇의 약자인가?\", \"options\": [\"A\", \"B\", \"C\", \"D\"], \"answer\": \"2\" \"comment\": \"그 이유는~~\" }이렇게 반한해줘 ";

        // 각 메시지를 HumanMessage로 변환하여 메시지 리스트로 생성
        List<Map<String, String>> messageList = List.of(
                Map.of("type", "human", "content", levelPrompt)
        );
        // 요청 본체 생성 (messageList를 포함한 RequestBody 객체 생성)
        RequestBody requestBody = new RequestBody(messageList);

        // POST 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(requestBody, new HttpHeaders()),
                String.class
        );

        if (response.getBody() == null) {
            return "문제 생성 실패";
        }

        try {
            String responseBody = response.getBody();  // 전체 응답에서 JSON 문자열 추출
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseBody);

            JsonNode responseJson = mapper.readTree(jsonNode.get("response").asText().replace("`", ""));
            System.out.println(responseJson);

            int quizLevel = responseJson.get("quizLevel").asInt();
            String category = responseJson.get("category").asText();
            String question = responseJson.get("question").asText();
            String options = responseJson.get("options").asText();
            String answer = responseJson.get("answer").asText();
            String comment = responseJson.get("comment").asText();

            System.out.println("Quiz Level: " + quizLevel);
            System.out.println("Category: " + category);
            System.out.println("Question: " + question);
            System.out.println("Options: " + options);
            System.out.println("Answer: " + answer);
            System.out.println("Comment: " + comment);

            Quiz quiz = Quiz.builder()
                    .quizCategory(category)
                    .quizAnswer(answer)
                    .quizQuestion(question)
                    .quizLevel(quizLevel)
                    .quizOption(options)
                    .quizComment(comment)
                    .build();

            quizRepository.save(quiz);
            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            return "퀴즈 저장 실패";
        }
    }

    // 요청 본체 클래스
    private static class RequestBody {
        private List<Map<String, String>> messages;

        public RequestBody(List<Map<String, String>> messages) {
            this.messages = messages;
        }

        public List<Map<String, String>> getMessages() {
            return messages;
        }

        public void setMessages(List<Map<String, String>> messages) {
            this.messages = messages;
        }
    }

    // 응답 본체 클래스
    private static class ResponseBody {
        private String response;

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }
    }
}
