package com.dvgiang.electricitybillingsystem.repository.permission;

import com.dvgiang.electricitybillingsystem.dto.query.PermissionActionDTO;
import com.dvgiang.electricitybillingsystem.dto.query.RolePermissionDTO;
import com.dvgiang.electricitybillingsystem.entity.QPermission;
import com.dvgiang.electricitybillingsystem.entity.QRole;
import com.dvgiang.electricitybillingsystem.entity.QRolePermission;
import com.dvgiang.electricitybillingsystem.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class PermissionRepositoryImpl extends BaseRepository implements PermissionRepositoryCustom {
    public PermissionRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<RolePermissionDTO> getRolePermissionsByRoleId(Long id) {
        QRolePermission qRolePermission = QRolePermission.rolePermission;
        QRole qRole = QRole.role;
        QPermission qPermission = QPermission.permission;

        return query.from(qRole)
                .innerJoin(qRolePermission).on(qRole.id.eq(qRolePermission.roleId))
                .innerJoin(qPermission).on(qPermission.id.eq(qRolePermission.permissionId))
                .where(qRole.id.eq(id))
                .select(Projections.fields(RolePermissionDTO.class,
                        qRole.id.as("roleId"),
                        qRole.name.as("roleName"),
                        Projections.fields(PermissionActionDTO.class,
                                qPermission.id.as("permissionId"),
                                qPermission.title.as("permissionTitle"),
                                qPermission.name.as("permissionName"),
                                qRolePermission.canWrite,
                                qRolePermission.canView,
                                qRolePermission.canDelete
                        ).as("permission")
                ))
                .fetch();
    }

    @Override
    @Modifying
    @Transactional
    public long changePermissionByRoleId(Long roleId, Long permissionId, List<Path<?>> paths, List<?> values) {
        QRolePermission qRolePermission = QRolePermission.rolePermission;

        BooleanBuilder builder = new BooleanBuilder()
                .and(qRolePermission.roleId.eq(roleId))
                .and(qRolePermission.permissionId.eq(permissionId));

        return query.update(qRolePermission)
                .where(builder)
                .set(paths, values)
                .set(qRolePermission.updatedAt, new Date())
                .execute();
    }
}
