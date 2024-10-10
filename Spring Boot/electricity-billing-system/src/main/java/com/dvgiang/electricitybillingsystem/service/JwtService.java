package com.dvgiang.electricitybillingsystem.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {
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
        .setExpiration(new Date(currentTimeMillis + 6*60*60*1000)) //token ton tai trong 6h
        .setIssuer("electricity_billing_sys")
        .signWith(getSignKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean isValidToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return !isExpiredToken(token) && (username.equals(userDetails.getUsername()));
  }

  /*
  * True, nếu token hết hạn
  * Ngược lại là False
  */
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
