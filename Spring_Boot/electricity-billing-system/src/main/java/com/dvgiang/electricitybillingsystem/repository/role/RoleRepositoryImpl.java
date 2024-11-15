package com.dvgiang.electricitybillingsystem.repository.role;

import com.dvgiang.electricitybillingsystem.entity.QPermission;
import com.dvgiang.electricitybillingsystem.entity.QRole;
import com.dvgiang.electricitybillingsystem.entity.QRolePermission;
import com.dvgiang.electricitybillingsystem.entity.Role;
import com.dvgiang.electricitybillingsystem.enums.PermissionType;
import com.dvgiang.electricitybillingsystem.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryImpl extends BaseRepository implements RoleRepositoryCustom {
    public RoleRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        QRole qRole = QRole.role;
        return Optional.ofNullable(query.from(qRole)
                .where(qRole.name.eq(name))
                .select(qRole)
                .fetchOne()
        );
    }

    @Override
    public Long getRoleIDByName(String name) {
        QRole qRole = QRole.role;
        return query.from(qRole)
                .where(qRole.name.eq(name))
                .select(qRole.id)
                .fetchOne();
    }

    @Override
    public boolean existPermission(long roleId, String permission, PermissionType type) {
        QRolePermission qRolePermission = QRolePermission.rolePermission;
        QPermission qPermission = QPermission.permission;

        BooleanBuilder builder = new BooleanBuilder()
                .and(qRolePermission.roleId.eq(roleId))
                .and(qPermission.name.eq(permission));
        if (type != null) {
            builder.and(switch (type) {
                case VIEW -> qRolePermission.canView.eq(true);
                case WRITE -> qRolePermission.canWrite.eq(true);
                case DELETE -> qRolePermission.canDelete.eq(true);
            });
        }

        Long count = query.from(qRolePermission)
                .innerJoin(qPermission).on(qRolePermission.permissionId.eq(qPermission.id))
                .where(builder)
                .select(qRolePermission.id.count())
                .fetchOne();
        return count != null && count > 0;
    }
}
