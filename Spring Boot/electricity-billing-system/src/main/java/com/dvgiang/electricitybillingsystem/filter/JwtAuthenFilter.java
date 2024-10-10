package com.dvgiang.electricitybillingsystem.filter;

import com.dvgiang.electricitybillingsystem.service.JwtService;
import com.dvgiang.electricitybillingsystem.service.UserService;
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
  public final UserDetailsService userDetailsService;
//  public final UserService userService;
  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    //Lay token xac thuc tu header cua request
    final String authHeader = request.getHeader("Authorization");

    final String jwtToken;
    final String username;

    /*
    * Kiem tra kieu cua ma xac thuc (token)
    * JWT thuong bat dau voi 7 ky tu dau la: "Bearer "
    */
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    //Lay ma token
    jwtToken = authHeader.substring(7);
    //Giai ma username tu token
    username = jwtService.extractUsername(jwtToken);

    /*
     * SecurityContextHolder.getContext().getAuthentication(): Lấy ngữ cảnh xác thực
     * Nếu là null, username chưa được xác thực
     * Tiến hành xác thực
     */
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      if (jwtService.isValidToken(jwtToken, userDetails)) {
        //Tao doi tuong dai dien cho thong tin xác thuc, truyen 3 tham so
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails, //Thong tin nguoi dung
            null, //Thong tin xac thuc, truyen null vi xac thuc da hoan tat bang token. Nếu chưa xác thực thì thường là mật khẩu hoặc thông tin xác thực khác
            userDetails.getAuthorities() //Cac quyen ma nguoi dung co
        );
        //Đưa thêm thông tin request vào chi tiết của mã xác thực
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        //Cập nhật chủ sở hữu bối cảnh bảo mật
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    //Chuyển tiếp đến các bộ lọc khác sau khi hoàn tất
    filterChain.doFilter(request, response);
  }
}
