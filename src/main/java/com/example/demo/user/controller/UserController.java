package com.example.demo.user.controller;

import com.example.demo.user.CustomUserDetails;
import com.example.demo.user.JwtToken;
import com.example.demo.user.model.User;
import com.example.demo.user.model.UserInfoReq;
import com.example.demo.user.model.UserLoginReq;
import com.example.demo.user.model.UserSignupReq;
import com.example.demo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    @Tag(name = "회원가입 api")
    public ResponseEntity<String> signUp(@RequestBody UserSignupReq userDto) {
        if (userDto.getUserId() == null || userDto.getUserPassword() == null ||
                userDto.getUserName() == null || userDto.getUserNickname() == null ||
                userDto.getUserEmail() == null) {
            return ResponseEntity.badRequest().body("모든 필드를 입력해주세요.");
        }

        User user = User.builder()
                .userId(userDto.getUserId())
                .userNickname(userDto.getUserNickname())
                .userName(userDto.getUserName())
                .userPassword(userDto.getUserPassword())
                .userBirthday(userDto.getUserBirthday())
                .userEmail(userDto.getUserEmail())
                .build();

        try {
            userService.singUp(user);
            return ResponseEntity.ok("{\"message\":\"User successfully registered.\"}");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\":\"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"서버 오류가 발생했습니다.\"}");
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // 상태 코드는 409로 설정
        return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\":\"" + ex.getMessage() + "\"}");
    }


    @PutMapping("/info")
    @Tag(name = "회원수정(닉네임 수정) api")
    public ResponseEntity<String> updateUserInfo(@RequestBody UserInfoReq userInfoReq) {
        String newNickname = userInfoReq.getNewNickname();
        userService.updateUserInfo(newNickname);
        return ResponseEntity.ok("User successfully updated.");
    }

    @PostMapping("/login")
    @Tag(name = "로그인 api")
    public ResponseEntity<String> login(@RequestBody UserLoginReq loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getUserPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = userService.generateJwtToken(userDetails);
        return ResponseEntity.ok(token);
    }
}

