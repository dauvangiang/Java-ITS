package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.UserDetailsImpl;
import com.dvgiang.electricitybillingsystem.entity.User;
import com.dvgiang.electricitybillingsystem.enums.PermissionType;
import com.dvgiang.electricitybillingsystem.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/*
 * T1: Repository
 * T2: Mapper
 */
public class BaseService<T1, T2> {
    protected T1 repository;
    protected T2 mapper;

    @Autowired
    private RoleRepository roleRepository;

    public BaseService(T1 repository, T2 mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    protected User getUser() {
        try {
            Authentication authen = SecurityContextHolder.getContext().getAuthentication();
            if (
                    authen != null && authen.isAuthenticated()
                            && !authen.getName().equals("anonymousUser")
                            && authen.getPrincipal() instanceof UserDetailsImpl userDetails
            ) {
                return userDetails.getUser();
            }
        } catch (Exception _) {
        }
        throw new AuthenticationCredentialsNotFoundException("Vui lòng đăng nhập để tiếp tục!");
    }

    protected User getUser(String permission, PermissionType type) {
        User user = getUser();
        if (permission == null) {
            return user;
        }
        if (roleRepository.existPermission(user.getRoleId(), permission, type)) {
            return user;
        }
        throw new AccessDeniedException("Bạn không có quyền thực hiện tác vụ này!");
    }
}
