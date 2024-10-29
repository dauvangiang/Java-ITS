package com.dvgiang.electricitybillingsystem.repository.role;

import com.dvgiang.electricitybillingsystem.entity.Role;

import java.util.Optional;

public interface RoleRepositoryCustom {
    Optional<Role> getRoleByName(String name);
}
