package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.request.LoginDTO;
import com.dvgiang.electricitybillingsystem.dto.request.RegisterDTO;
import com.dvgiang.electricitybillingsystem.dto.response.AuthenticationResponseDTO;
import com.dvgiang.electricitybillingsystem.entity.Permission;
import com.dvgiang.electricitybillingsystem.entity.User;
import com.dvgiang.electricitybillingsystem.mapper.UserMapper;
import com.dvgiang.electricitybillingsystem.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenManager;
    private final JwtService jwtService;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public User creatNewUser(RegisterDTO registerDTO) {
        registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        User user = mapper.toUser(registerDTO);
        user.setRole(roleService.getRoleByName("TECHNICIAN"));

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
        User user = userRepository.getUserByUsername(loginDTO.getUsername())
                .orElseThrow();
        String token = jwtService.generateToken(user);

        Set<String> permissions = user.getRole().getPermissions().stream()
                .map(Permission::getName).collect(Collectors.toSet());

        return AuthenticationResponseDTO.builder()
                .method("Bearer")
                .type("JWT")
                .token(token)
                .permissions(permissions)
                .build();
    }
}
