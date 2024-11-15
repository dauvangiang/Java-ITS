package com.dvgiang.electricitybillingsystem.dto.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionActionDTO {
    private Long permissionId;
    private String permissionTitle;
    private String permissionName;
    private Boolean canWrite;
    private Boolean canView;
    private Boolean canDelete;
}
