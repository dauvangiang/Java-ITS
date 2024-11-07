package com.dvgiang.electricitybillingsystem.repository.permission;

import com.dvgiang.electricitybillingsystem.entity.Permission;
import com.dvgiang.electricitybillingsystem.entity.QPermission;
import com.dvgiang.electricitybillingsystem.entity.QRole;
import com.dvgiang.electricitybillingsystem.entity.QRolePermission;
import com.dvgiang.electricitybillingsystem.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionRepositoryImpl extends BaseRepository implements PermissionRepositoryCustom {
    public PermissionRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Permission> getPermissionsByRoleID(Long id) {
        QPermission qPermission = QPermission.permission;
        QRole qRole = QRole.role;
        QRolePermission qRolePermission = QRolePermission.rolePermission;

        return query.select(qPermission)
                .from(qPermission)
                .innerJoin(qRolePermission).on(qRolePermission.permissionId.eq(qPermission.id))
                .where(qRolePermission.roleId.eq(id))
                .fetch();
    }
}
