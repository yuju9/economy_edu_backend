package com.example.demo.user;

import com.example.demo.user.model.CustomUserInfoDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private final SecretKey key; // secretKey 대신 key로 변경
    private final long accessTokenExpTime;

    public JwtUtil(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration_time}") long accessTokenExpTime  // 만료 시간
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpTime = accessTokenExpTime;
    }

    // 사용자 정보로 Access Token 생성
    public String createAccessToken(CustomUserInfoDto user) {
        return generateToken(user, accessTokenExpTime);
    }

    // 토큰에서 사용자 이름 추출
    public String getUserIdFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    // 토큰에서 모든 클레임 추출
    private Claims getAllClaimsFromToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰 생성
    public String generateToken(CustomUserInfoDto user, long expireTime) {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expireTime);
        return Jwts.builder()
                .setSubject(user.getUserId())  // 사용자 이름 설정
                .setIssuedAt(new Date())  // 현재 시간
                .setExpiration(Date.from(tokenValidity.toInstant()))  // 만료 시간
                .signWith(key, SignatureAlgorithm.HS256)  // 서명 알고리즘과 키 설정
                .compact();
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);  // key를 사용하여 파싱
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 토큰", e);
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰", e);
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT 클레임 문자열이 비어있습니다.", e);
        }
        return false;
    }
}
