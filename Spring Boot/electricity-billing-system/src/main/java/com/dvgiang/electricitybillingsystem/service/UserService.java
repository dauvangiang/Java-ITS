package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.request.LoginDTO;
import com.dvgiang.electricitybillingsystem.dto.request.RegisterDTO;
import com.dvgiang.electricitybillingsystem.dto.response.AuthenticationResponseDTO;
import com.dvgiang.electricitybillingsystem.model.Role;
import com.dvgiang.electricitybillingsystem.repository.UserRepository;
import com.dvgiang.electricitybillingsystem.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenManager;
  private final JwtService jwtService;

  public User creatNewUser(RegisterDTO registerDTO) {
    User user = User
        .builder()
        .username(registerDTO.getUsername())
        .password(passwordEncoder.encode(registerDTO.getPassword()))
        .email(registerDTO.getEmail())
        .phone(registerDTO.getPhone())
        .fullName(registerDTO.getFullName())
        .address(registerDTO.getAddress())
        .role(Role.TECHNICIAN)
        .build();
    return userRepository.save(user);
  }

  public AuthenticationResponseDTO authentication(LoginDTO loginDTO) {
    //Xác thực thông tin đăng nhập, đối tượng Authentication được tạo ra
    //Có ngoại lệ nếu ko thành công
    authenManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginDTO.getUsername(), //thông tin định danh
            loginDTO.getPassword() //thông tin bảo mật
        )
    );
    //Lấy thông tin người dùng sau khi xác thực thành công
    User user = userRepository.findUserByUsername(loginDTO.getUsername())
        .orElseThrow();
    String token = jwtService.generateToken(user);
    return AuthenticationResponseDTO.builder()
        .method("Bearer")
        .type("JWT")
        .token(token)
        .build();
  }
}
