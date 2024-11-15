package com.dvgiang.electricitybillingsystem.repository.role;

import com.dvgiang.electricitybillingsystem.entity.Role;
import com.dvgiang.electricitybillingsystem.enums.PermissionType;

import java.util.Optional;

public interface RoleRepositoryCustom {
    Optional<Role> getRoleByName(String name);
    Long getRoleIDByName(String name);
    boolean existPermission(long roleId, String permission, PermissionType type);
}
