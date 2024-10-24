package com.example.demo.user.service;

import com.example.demo.user.CustomUserDetails;
import com.example.demo.user.model.CustomUserInfoDto;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findByUserId(userId)
                .map(user -> new CustomUserDetails(new CustomUserInfoDto(user)))  // User -> CustomUserInfoDto 변환
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}