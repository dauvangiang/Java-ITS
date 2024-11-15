package com.dvgiang.electricitybillingsystem.service.permission;

import com.dvgiang.electricitybillingsystem.dto.query.RolePermissionDTO;
import com.dvgiang.electricitybillingsystem.dto.request.RolePermissionRequestDTO;
import com.dvgiang.electricitybillingsystem.entity.QRolePermission;
import com.dvgiang.electricitybillingsystem.enums.PermissionType;
import com.dvgiang.electricitybillingsystem.mapper.permission.PermissionMapper;
import com.dvgiang.electricitybillingsystem.repository.permission.PermissionRepository;
import com.dvgiang.electricitybillingsystem.service.BaseService;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl extends BaseService<PermissionRepository, PermissionMapper> implements PermissionService{
    public PermissionServiceImpl(PermissionRepository repository, PermissionMapper mapper) {
        super(repository, mapper);
    }

    private final String permission = "ROLE_PERMISSION_MANAGEMENT";

    @Override
    public Map<String, Object> getRolePermissionsByRoleId(Long id) {
        super.getUser(permission, PermissionType.VIEW);
        List<RolePermissionDTO> rolePermissionDTOS = repository.getRolePermissionsByRoleId(id);

        List<Object> permissions = new ArrayList<>();
        for (RolePermissionDTO obj : rolePermissionDTOS) {
            permissions.add(obj.getPermission());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("roleId", rolePermissionDTOS.getFirst().getRoleId());
        result.put("roleName", rolePermissionDTOS.getFirst().getRoleName());
        result.put("permissions", permissions);

        return result;
    }

    @Override
    public long changePermissionByRoleId(RolePermissionRequestDTO dto) {
        super.getUser(permission, PermissionType.WRITE);
        List<Path<?>> paths = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        QRolePermission qRolePermission = QRolePermission.rolePermission;

        for (Field field : dto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                PathBuilder<Object> path = new PathBuilder<>(Object.class, qRolePermission.getMetadata().getName() + "." + field.getName());
                paths.add(path);
                Object value = field.get(dto);
                if (value != null) {
                    values.add(value);
                }
            } catch (Exception _) {
            }
        }

        return repository.changePermissionByRoleId(dto.getRoleId(), dto.getPermissionId(), paths, values);
    }
}
