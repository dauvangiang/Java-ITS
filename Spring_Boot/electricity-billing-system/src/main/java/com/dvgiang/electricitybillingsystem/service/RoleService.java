package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.entity.Role;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.repository.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name)
                .orElseThrow(() -> new NotFoundException("Role has name = " + name + " does not exist!"));
    }
}
