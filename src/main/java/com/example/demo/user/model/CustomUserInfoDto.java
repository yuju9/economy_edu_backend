package com.example.demo.user.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomUserInfoDto {
    private String userId;
    private String userPassword;
    private String role;

    // User 객체를 기반으로 CustomUserInfoDto를 생성하는 생성자 추가
    public CustomUserInfoDto(User user) {
        this.userId = user.getUserId();
        this.userPassword = user.getUserPassword();
        this.role = user.getRole();
    }

}
