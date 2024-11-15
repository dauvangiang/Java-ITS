package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.RolePermissionRequestDTO;
import com.dvgiang.electricitybillingsystem.dto.response.BaseResponse;
import com.dvgiang.electricitybillingsystem.service.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role_permissions")
@RequiredArgsConstructor
public class RolePermissionController {
    private final PermissionService permissionService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRolePermissionByRoleId(@PathVariable Long id) {
        return  ResponseEntity.ok(
                BaseResponse.ok(permissionService.getRolePermissionsByRoleId(id))
        );
    }

    @PostMapping("/change_permission")
    public ResponseEntity<Object> changePermissionByRoleId(@RequestBody RolePermissionRequestDTO dto) {
        return ResponseEntity.ok(
                BaseResponse.ok(permissionService.changePermissionByRoleId(dto))
        );
    }
}
