package com.dvgiang.electricitybillingsystem.service.user;

import com.dvgiang.electricitybillingsystem.dto.UserDetailsImpl;
import com.dvgiang.electricitybillingsystem.dto.query.PermissionActionDTO;
import com.dvgiang.electricitybillingsystem.entity.User;
import com.dvgiang.electricitybillingsystem.repository.user.UserRepository;
import com.dvgiang.electricitybillingsystem.service.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Thông tin người dùng không tồn tại!"));

//        List<PermissionActionDTO> permissions = (List<PermissionActionDTO>) permissionService.getRolePermissionsByRoleId(user.getRoleId()).get("permissions");
//
//        Collection<GrantedAuthority> authorities = permissions.stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
//                .collect(Collectors.toSet());

        return new UserDetailsImpl(user, null);
    }
}
