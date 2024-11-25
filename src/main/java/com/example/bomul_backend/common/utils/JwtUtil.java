package com.example.bomul_backend.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;

@Component
public class JwtUtil {
    private final SecretKey secretKey;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    /**
     * 헤더에서 JWT 토큰 값을 가져오는 메서드
     * @param request ServerHttpRequest 객체
     * @return 쿠키 값 (Optional로 반환)
     */
    public Optional<String> getJwtTokenFromCookies(ServerHttpRequest request) {
        // 헤더에서 쿠키 문자열 가져오기
        String cookieHeader = request.getHeaders().getFirst(HttpHeaders.COOKIE);
        // JWT Token keys
        final String jwtTokenKeys = "AuthToken";

        if (cookieHeader != null) {
            // 쿠키 문자열을 ";"로 나누고 원하는 쿠키 이름 찾기
            return Arrays.stream(cookieHeader.split(";"))
                    .map(String::trim) // 공백 제거
                    .filter(cookie -> cookie.startsWith(jwtTokenKeys + "=")) // 쿠키 이름과 일치하는지 확인
                    .map(cookie -> cookie.substring((jwtTokenKeys + "=").length())) // 쿠키 값만 추출
                    .findFirst();
        }

        return Optional.empty(); // 쿠키가 없으면 빈 Optional 반환
    }

    public String generateToken(String gameCode) {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(gameCode)
                .issuer("GatheringPT")
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(3600 * 24 * 2)))
                .signWith(secretKey)
                .compact();
    }

    public Claims validateToken(String token) {
        return Jwts.parser()
                .decryptWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token, String subject) {
        try {
            Claims claims = validateToken(token);
            return claims.getSubject().equals(subject);
        } catch (Exception e) {
            return false;
        }
    }
}
