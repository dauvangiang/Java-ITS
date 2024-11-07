package com.dvgiang.electricitybillingsystem.filter;

import com.dvgiang.electricitybillingsystem.dto.response.BaseResponse;
import com.dvgiang.electricitybillingsystem.exception.ForbiddenException;
import com.dvgiang.electricitybillingsystem.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

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
             */
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtService.isValidToken(jwtToken, userDetails)) {
                    //Đối tượng đại diện cho thông tin xác thực
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, //Thông tin người dùng
                            null, //Thông tin xác thực
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

            String jsonResponse = (new ObjectMapper()).writeValueAsString(
                    BaseResponse.fail("JWT token validation failed! " + e.getMessage())
            );

            response.getWriter().write(jsonResponse);
            return;
        }
        catch (ForbiddenException e) {
            response.setStatus(403);
            response.setContentType("application/json");

            String jsonResponse = (new ObjectMapper()).writeValueAsString(
                    BaseResponse.fail(e.getMessage())
            );

            response.getWriter().write(jsonResponse);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
