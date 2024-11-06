package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.entity.Permission;
import com.dvgiang.electricitybillingsystem.repository.permission.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository repository;
    public boolean hasPermission(String permission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(permission));
        }
        return false;
    }

    public List<Permission> getPermissionsByRoleID(Long id) {
        return repository.getPermissionsByRoleID(id);
    }
}
