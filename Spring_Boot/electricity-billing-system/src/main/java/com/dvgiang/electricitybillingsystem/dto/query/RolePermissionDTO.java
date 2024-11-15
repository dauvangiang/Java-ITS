package com.dvgiang.electricitybillingsystem.dto.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePermissionDTO {
    private Long roleId;
    private String roleName;
    private PermissionActionDTO permission;
}
