package com.dvgiang.electricitybillingsystem.mapper.user;

import com.dvgiang.electricitybillingsystem.dto.request.UserDTO;
import com.dvgiang.electricitybillingsystem.entity.User;
import com.dvgiang.electricitybillingsystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleService roleService;

    public User toUser(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .roleId(roleService.getRoleIDByName("TECHNICIAN"))
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }
}
