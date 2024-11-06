package com.dvgiang.electricitybillingsystem.repository.permission;

import com.dvgiang.electricitybillingsystem.entity.Permission;
import com.dvgiang.electricitybillingsystem.entity.QPermission;
import com.dvgiang.electricitybillingsystem.entity.QRole;
import com.dvgiang.electricitybillingsystem.entity.QUser;
import com.dvgiang.electricitybillingsystem.repository.BaseRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
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

        return query.select(qPermission)
                .from(qRole)
                .join(qRole.permissions, qPermission)
                .where(qRole.id.eq(id))
                .fetch();

    }
}
