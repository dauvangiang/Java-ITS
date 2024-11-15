package com.dvgiang.electricitybillingsystem.repository.permission;

import com.dvgiang.electricitybillingsystem.dto.query.RolePermissionDTO;
import com.dvgiang.electricitybillingsystem.dto.request.RolePermissionRequestDTO;
import com.querydsl.core.types.Path;

import java.util.List;

public interface PermissionRepositoryCustom {
    List<RolePermissionDTO> getRolePermissionsByRoleId(Long id);
    long changePermissionByRoleId(Long roleId, Long permissionId, List<Path<?>> paths, List<?> values);
}
