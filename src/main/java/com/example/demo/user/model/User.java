package com.example.demo.user.model;

import com.example.demo.quiz.model.QuizResult;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @Column(nullable = false, length = 100, unique = true)
    private String userId;

    @Column(nullable = false, length = 100, unique = true)
    private String userNickname;

    @Column(nullable = false, length = 50)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false, length = 8)
    private String userBirthday;

    @Column(nullable = false, length = 20)
    private String userEmail;

    @Enumerated(EnumType.STRING)
    private UserState userState;

    @Column(nullable = false, length = 10)
    private int userLevel;

    @Column(nullable = false)
    private String role; // e.g., "USER", "ADMIN"

    @CreationTimestamp
    private Timestamp createDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuizResult> quizResults;
}
