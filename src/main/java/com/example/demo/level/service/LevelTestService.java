package com.example.demo.level.service;

import com.example.demo.user.CustomUserDetails;
import com.example.demo.user.model.User;
import com.example.demo.user.model.UserState;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LevelTestService {
    private final UserRepository userRepository;

    @Transactional
    public void updateUserLevel(int userLevel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserId = authentication.getName();
        User user = userRepository.findByUserId(currentUserId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setUserLevel(userLevel);
        user.setUserState(UserState.ACTIVE);
        user.setUserExp(0);
        userRepository.save(user);
    }

}
