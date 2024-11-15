package com.dvgiang.electricitybillingsystem.service.user;

import com.dvgiang.electricitybillingsystem.dto.request.LoginDTO;
import com.dvgiang.electricitybillingsystem.dto.request.UserDTO;
import com.dvgiang.electricitybillingsystem.dto.response.AuthenticationResponseDTO;
import com.dvgiang.electricitybillingsystem.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(UserDTO dto);
    AuthenticationResponseDTO authentication(LoginDTO loginDTO);
}
