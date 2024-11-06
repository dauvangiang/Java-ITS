package com.dvgiang.electricitybillingsystem.repository.permission;

import com.dvgiang.electricitybillingsystem.entity.Permission;

import java.util.List;

public interface PermissionRepositoryCustom {
    List<Permission> getPermissionsByRoleID(Long id);
}
