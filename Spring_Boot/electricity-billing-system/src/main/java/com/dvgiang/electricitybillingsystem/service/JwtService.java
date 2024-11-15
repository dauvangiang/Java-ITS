package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.entity.RevokedToken;
import com.dvgiang.electricitybillingsystem.repository.token.RevokedTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {
    private final RevokedTokenRepository revokedTokenRepository;

    @Value("${secretKey}")
    private String SECRET_KEY;

    @Value("${tokenExpirationMS}")
    private Long TOKEN_EXPIRATION_MS;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Long extractUserID(String token) {
        return Long.valueOf(extractClaim(token, Claims::getSubject));
    }

    public String generateToken(String username) {
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_MS))
                .setIssuer("electricity_billing_sys")
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public void revokeToken(String authHeader) {
        String token = authHeader.substring(7);
        RevokedToken revokedToken = RevokedToken
                .builder()
                .token(token)
                .expiredAt(new Date())
                .build();
        revokedTokenRepository.save(revokedToken);
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        /*
         * Nếu còn thời gian sống và người dùng hợp lệ
         * Kiểm tra mã trong blacklist, nếu tồn tại => mã không hợp lệ và ngược lại
         */
        return !isExpiredToken(token)
                && username.equals(userDetails.getUsername())
                && !revokedTokenRepository.existsByToken(token);
    }

    private boolean isExpiredToken(String token) {
        return extractExpired(token).before(new Date());
    }

    private Date extractExpired(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
