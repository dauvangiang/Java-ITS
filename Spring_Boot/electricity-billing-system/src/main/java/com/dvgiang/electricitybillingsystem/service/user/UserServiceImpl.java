package com.dvgiang.electricitybillingsystem.service.user;

import com.dvgiang.electricitybillingsystem.dto.request.LoginDTO;
import com.dvgiang.electricitybillingsystem.dto.request.UserDTO;
import com.dvgiang.electricitybillingsystem.dto.response.AuthenticationResponseDTO;
import com.dvgiang.electricitybillingsystem.entity.Permission;
import com.dvgiang.electricitybillingsystem.entity.User;
import com.dvgiang.electricitybillingsystem.mapper.user.UserMapper;
import com.dvgiang.electricitybillingsystem.repository.user.UserRepository;
import com.dvgiang.electricitybillingsystem.service.BaseService;
import com.dvgiang.electricitybillingsystem.service.JwtService;
import com.dvgiang.electricitybillingsystem.service.permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseService<UserRepository, UserMapper> implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PermissionService permissionService;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public User createUser(UserDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = mapper.toUser(dto);
        return repository.save(user);
    }

    @Override
    public AuthenticationResponseDTO authentication(LoginDTO loginDTO) {
        //Xác thực thông tin đăng nhập, đối tượng Authentication được tạo ra
        //Có ngoại lệ nếu ko thành công
        authenManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );

        //Lấy thông tin người dùng sau khi xác thực thành công
        User user = repository.getUserByUsername(loginDTO.getUsername())
                .orElseThrow();
        String token = jwtService.generateToken(user.getUsername());

        return AuthenticationResponseDTO.builder()
                .method("Bearer")
                .type("JWT")
                .token(token)
                .build();
    }

    @Override
    public List<Permission> getPermissions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            String username = authentication.getName();
            Long roleID = repository.getRoleIDByUsername(username);
            return permissionService.getPermissionsByRoleID(roleID);
        }
        return null;
    }
}
