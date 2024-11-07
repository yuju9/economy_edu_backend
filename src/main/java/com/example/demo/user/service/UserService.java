package com.example.demo.user.service;

import com.example.demo.user.CustomUserDetails;
import com.example.demo.user.JwtUtil;
import com.example.demo.user.model.CustomUserInfoDto;
import com.example.demo.user.model.User;
import com.example.demo.user.model.UserLoginReq;
import com.example.demo.user.model.UserState;
import com.example.demo.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encode;
    private final JwtUtil jwtUtil;

    @Transactional
    public void singUp(User user) {
        if (userRepository.existsByUserId(user.getUserId())) {
            throw new IllegalArgumentException("사용자 ID가 이미 존재합니다.");
        }

        if (userRepository.existsByUserNickname(user.getUserNickname())) {
            throw new IllegalArgumentException("닉네임이 이미 존재합니다.");
        }

        String rawPassword = user.getUserPassword(); // 비밀번호 원문
        String encPassword = encode.encode(rawPassword); // 해쉬로 바꿈
        user.setUserPassword(encPassword); // 암호화된 비밀번호 설정
        user.setUserState(UserState.ACTIVE); // 기본 사용자 권한 설정
        user.setUserLevel(1);
        user.setRole("USER");
        userRepository.save(user); // DB에 저장
    }

    @Transactional
    public void updateUserInfo(String newNickname) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("현재 사용자 ID: {}", userDetails.getUsername());

        String currentUserId = userDetails.getUsername();
        User user = userRepository.findByUserId(currentUserId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (newNickname != null && !newNickname.isEmpty()) {
            log.info("닉네임 업데이트: {}", newNickname);
            user.setUserNickname(newNickname);
            userRepository.save(user);

        }
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findByUserId(username) // username을 사용하여 사용자 검색
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }


    public String generateJwtToken(CustomUserDetails userDetails) {
        return jwtUtil.createAccessToken(userDetails.getMember());
    }
}

