package com.dvgiang.electricitybillingsystem.service.role;

import com.dvgiang.electricitybillingsystem.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getRoleByName(String name);
    Long getRoleIDByName(String name);
}
