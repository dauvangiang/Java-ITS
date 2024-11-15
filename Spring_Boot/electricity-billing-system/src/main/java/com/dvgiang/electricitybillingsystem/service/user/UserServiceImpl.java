package com.dvgiang.electricitybillingsystem.service.user;

import com.dvgiang.electricitybillingsystem.dto.request.LoginDTO;
import com.dvgiang.electricitybillingsystem.dto.request.UserDTO;
import com.dvgiang.electricitybillingsystem.dto.response.AuthenticationResponseDTO;
import com.dvgiang.electricitybillingsystem.entity.User;
import com.dvgiang.electricitybillingsystem.mapper.user.UserMapper;
import com.dvgiang.electricitybillingsystem.repository.user.UserRepository;
import com.dvgiang.electricitybillingsystem.service.BaseService;
import com.dvgiang.electricitybillingsystem.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseService<UserRepository, UserMapper> implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenManager;
    private final JwtService jwtService;

    public UserServiceImpl(
            UserRepository repository, UserMapper mapper,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenManager,
            JwtService jwtService
    ) {
        super(repository, mapper);
        this.passwordEncoder = passwordEncoder;
        this.authenManager = authenManager;
        this.jwtService = jwtService;
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
}
