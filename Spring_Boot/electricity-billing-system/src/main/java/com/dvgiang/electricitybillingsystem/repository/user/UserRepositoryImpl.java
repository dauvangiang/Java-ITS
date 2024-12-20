package com.dvgiang.electricitybillingsystem.repository.user;

import com.dvgiang.electricitybillingsystem.entity.QUser;
import com.dvgiang.electricitybillingsystem.entity.User;
import com.dvgiang.electricitybillingsystem.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl extends BaseRepository implements UserRepositoryCustom {
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        QUser qUser = QUser.user;
        return Optional.ofNullable(query.from(qUser)
                .where(qUser.username.eq(username))
                .select(qUser)
                .fetchOne()
        );
    }

    @Override
    public Long getRoleIDByUsername(String username) {
        QUser qUser = QUser.user;
        return query.from(qUser)
                .where(qUser.username.eq(username))
                .select(qUser.roleId)
                .fetchOne();
    }
}
