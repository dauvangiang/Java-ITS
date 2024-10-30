package com.dvgiang.electricitybillingsystem.repository.role;

import com.dvgiang.electricitybillingsystem.entity.QRole;
import com.dvgiang.electricitybillingsystem.entity.Role;
import com.dvgiang.electricitybillingsystem.repository.BaseRepository;
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
        return Optional.ofNullable(
                query.from(qRole)
                        .select(qRole)
                        .where(qRole.name.eq(name))
                        .fetchOne()
        );
    }
}
