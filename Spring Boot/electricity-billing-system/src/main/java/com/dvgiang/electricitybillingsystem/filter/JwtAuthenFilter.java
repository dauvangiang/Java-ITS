package com.dvgiang.electricitybillingsystem.filter;

import com.dvgiang.electricitybillingsystem.exception.ForbiddenException;
import com.dvgiang.electricitybillingsystem.service.JwtService;
import com.dvgiang.electricitybillingsystem.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  public final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");

    /*
    * Kiểm tra kiểu của mã xác thực (token)
    * Mã bắt đầu với 7 ký tự: "Bearer "
    */
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String jwtToken = authHeader.substring(7);
    try {
      final String username = jwtService.extractUsername(jwtToken);

      /*
       * SecurityContextHolder.getContext().getAuthentication(): Lấy ngữ cảnh xác thực
       * Nếu là null, username chưa được xác thực
       * Tiến hành xác thực
       */
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        if (jwtService.isValidToken(jwtToken, userDetails)) {
          //Đối tượng đại diện cho thông tin xác thực
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              userDetails, //Thông tin người dùng
              null, //Thong tin xac thuc, null vi da xac thuc bang token
              userDetails.getAuthorities() //quyền của người dùng
          );
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          //Cập nhật chủ sở hữu bối cảnh bảo mật
          SecurityContextHolder.getContext().setAuthentication(authToken);
        } else {
          throw new ForbiddenException("Resource cannot be accessed. Please log in again!");
        }
      }
    }
    catch (JwtException e) {
      response.setStatus(401);
      response.setContentType("application/json");

      Map<String, Object> responseData = new HashMap<>();
      responseData.put("message", "JWT token validation failed!");
      responseData.put("details", e.getMessage());
      responseData.put("status", HttpStatus.UNAUTHORIZED);
      responseData.put("statusCode", 401);

      String jsonResponse = (new ObjectMapper()).writeValueAsString(responseData);

      response.getWriter().write(jsonResponse);
      return;
    }
    catch (ForbiddenException e) {
      response.setStatus(403);
      response.setContentType("application/json");

      Map<String, Object> responseData = new HashMap<>();
      responseData.put("message", e.getMessage());
      responseData.put("status", HttpStatus.FORBIDDEN);
      responseData.put("statusCode", 403);

      String jsonResponse = (new ObjectMapper()).writeValueAsString(responseData);

      response.getWriter().write(jsonResponse);
      return;
    }

    filterChain.doFilter(request, response);
  }
}
