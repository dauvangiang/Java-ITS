package com.dvgiang.electricitybillingsystem.service.permission;


import com.dvgiang.electricitybillingsystem.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {
    boolean hasPermission(String permission);
    List<Permission> getPermissionsByRoleID(Long id);
}
