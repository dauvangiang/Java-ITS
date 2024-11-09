package com.dvgiang.electricitybillingsystem.service.permission;

import com.dvgiang.electricitybillingsystem.entity.Permission;
import com.dvgiang.electricitybillingsystem.mapper.permission.PermissionMapper;
import com.dvgiang.electricitybillingsystem.repository.permission.PermissionRepository;
import com.dvgiang.electricitybillingsystem.service.BaseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends BaseService<PermissionRepository, PermissionMapper> implements PermissionService{
    public PermissionServiceImpl(PermissionRepository repository, PermissionMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public boolean hasPermission(String permission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(permission));
        }
        return false;
    }

    @Override
    public List<Permission> getPermissionsByRoleID(Long id) {
        return repository.getPermissionsByRoleID(id);
    }
}
