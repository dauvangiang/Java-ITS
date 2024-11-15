package com.dvgiang.electricitybillingsystem.service.permission;


import com.dvgiang.electricitybillingsystem.dto.query.RolePermissionDTO;
import com.dvgiang.electricitybillingsystem.dto.request.RolePermissionRequestDTO;
import com.querydsl.core.types.Path;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PermissionService {
    Map<String, Object> getRolePermissionsByRoleId(Long id);
    long changePermissionByRoleId(RolePermissionRequestDTO dto);
}
