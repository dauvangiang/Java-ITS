package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.entity.TokenRevoked;
import com.dvgiang.electricitybillingsystem.repository.TokenRevokedRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {
    private final TokenRevokedRepository tokenRevokedRepository;

    private static final String SECRET_KEY = "404E635266556A586e3272357538782F413F4428472B4B6250645367566b5970";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

//  public String generateToken(UserDetails userDetails) {
//    return generateToken(new HashMap<>(), userDetails);
//  }
//
//  //Thêm các claim bổ sung từ extractClaim được truyền vào
//  public String generateToken(Map<String, Objects> extractClaim, UserDetails userDetails) {
//    long currentTimeMillis = System.currentTimeMillis();
//    return Jwts
//        .builder()
//        .setClaims(extractClaim)
//        .setSubject(userDetails.getUsername())
//        .setIssuedAt(new Date(currentTimeMillis))
//        .setExpiration(new Date(currentTimeMillis + 6*60*60*1000)) //token ton tai trong 6h
//        .setIssuer("electricity_billing_sys")
//        .signWith(getSignKey(), SignatureAlgorithm.HS256)
//        .compact();
//  }

    public String generateToken(UserDetails userDetails) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 3*60*60*1000)) //token ton tai trong 3h
                .setIssuer("electricity_billing_sys")
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public void revokeToken(String authHeader) {
        String token = authHeader.substring(7);
        TokenRevoked tokenRevoked = TokenRevoked
                .builder()
                .token(token)
                .expiredAt(new Date(System.currentTimeMillis()))
                .build();
        //Lưu token vào blacklist
        tokenRevokedRepository.save(tokenRevoked);
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        /*
         * Nếu còn thời gian sống và người dùng hợp lệ
         * Kiểm tra mã trong blacklist, nếu tồn tại => mã không hợp lệ và ngược lại
         */
        return !isExpiredToken(token)
                && username.equals(userDetails.getUsername())
                && !tokenRevokedRepository.existsByToken(token);
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
                .parserBuilder() //Tạo đối tượng JwtParserBuilder cho phép phân tích jwt
                .setSigningKey(getSignKey()) //Đặt khóa bí mật dùng để xác thực jwt
                .build() //Tạo và trả về đối tượng JwtParser
                .parseClaimsJws(token) //Phân tích jwt
                .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
