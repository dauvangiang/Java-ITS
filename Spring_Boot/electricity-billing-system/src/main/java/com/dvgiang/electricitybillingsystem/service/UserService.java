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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    private final PermissionService permissionService;

    public User creatNewUser(RegisterDTO registerDTO) {
        registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        User user = mapper.toUser(registerDTO);
        user.setCreatedAt(new Date());
        user.setRoleId(roleService.getRoleIDByName("TECHNICIAN"));

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
        String token = jwtService.generateToken(user.getUsername());

        return AuthenticationResponseDTO.builder()
                .method("Bearer")
                .type("JWT")
                .token(token)
                .build();
    }

    public List<Permission> getPermissions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            String username = authentication.getName();
            Long roleID = userRepository.getRoleIDByUsername(username);
            return permissionService.getPermissionsByRoleID(roleID);
        }
        return null;
    }
}