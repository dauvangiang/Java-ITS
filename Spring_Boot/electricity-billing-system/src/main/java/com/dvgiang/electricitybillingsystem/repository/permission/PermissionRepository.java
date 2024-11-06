package com.dvgiang.electricitybillingsystem.repository.permission;

import com.dvgiang.electricitybillingsystem.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>, PermissionRepositoryCustom {
}
