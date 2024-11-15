package com.dvgiang.electricitybillingsystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePermissionRequestDTO {
    @NotNull(message = "ID vai trò không đươc để trống!")
    private Long roleId;

    @NotNull(message = "ID quyền hạn không đươc để trống!")
    private Long permissionId;

    private Boolean canWrite = false;
    private Boolean canView = false;
    private Boolean canDelete = false;
}
