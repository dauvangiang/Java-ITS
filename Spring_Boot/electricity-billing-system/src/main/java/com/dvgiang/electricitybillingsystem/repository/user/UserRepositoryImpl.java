package com.dvgiang.electricitybillingsystem.repository.user;

import com.dvgiang.electricitybillingsystem.entity.QUser;
import com.dvgiang.electricitybillingsystem.entity.User;
import com.dvgiang.electricitybillingsystem.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl extends BaseRepository<User, Long> implements UserRepositoryCustom {
    public UserRepositoryImpl(EntityManager entityManager) {
        super(User.class, entityManager);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        QUser qUser = QUser.user;
        return Optional.ofNullable(
                query.from(qUser)
                        .select(qUser)
                        .where(qUser.username.eq(username))
                        .fetchOne()
        );
    }
}
