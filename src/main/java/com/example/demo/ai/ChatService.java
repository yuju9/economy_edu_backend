//package com.example.demo.ai;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.util.List;
//
//@Service
//public class ChatService {
//
//    private final RestTemplate restTemplate;
//
//    @Autowired
//    public ChatService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public String generateResponse(List<String> messages) {
//        String url = "http://localhost:9001/api/generate-response";  // FastAPI URL
//
//        // 요청 본체 생성
//        RequestBody requestBody = new RequestBody(messages);
//
//        // POST 요청 보내기
//        ResponseBody response = restTemplate.postForObject(url, requestBody, ResponseBody.class);
//
//        // 응답에서 AI의 응답 메시지 반환
//        return response != null ? response.getResponse() : null;
//    }
//
//    // 요청 본체 클래스
//    private static class RequestBody {
//        private List<String> messages;
//
//        public RequestBody(List<String> messages) {
//            this.messages = messages;
//        }
//
//        public List<String> getMessages() {
//            return messages;
//        }
//
//        public void setMessages(List<String> messages) {
//            this.messages = messages;
//        }
//    }
//
//    // 응답 본체 클래스
//    private static class ResponseBody {
//        private String response;
//
//        public String getResponse() {
//            return response;
//        }
//
//        public void setResponse(String response) {
//            this.response = response;
//        }
//    }
//}
