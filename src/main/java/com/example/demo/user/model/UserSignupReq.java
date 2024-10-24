package com.example.demo.user.model;

import lombok.Data;

@Data
public class UserSignupReq {

    private String userId;
    private String userNickname;
    private String userName;
    private String userPassword;
    private String userBirthday;
    private String userEmail;

}
