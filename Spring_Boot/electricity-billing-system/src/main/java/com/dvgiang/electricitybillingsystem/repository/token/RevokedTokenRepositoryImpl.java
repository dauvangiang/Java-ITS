package com.dvgiang.electricitybillingsystem.repository.token;

import com.dvgiang.electricitybillingsystem.entity.QRevokedToken;
import com.dvgiang.electricitybillingsystem.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class RevokedTokenRepositoryImpl extends BaseRepository implements RevokedTokenRepositoryCustom {
    public RevokedTokenRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public boolean existsByToken(String token) {
        QRevokedToken qToken = QRevokedToken.revokedToken;
        return query.from(qToken)
                .where(qToken.token.eq(token))
                .select(qToken.id)
                .fetchFirst() != null;
    }

    @Override
    @Transactional
    @Modifying
    public void deleteAllOlderThan(Date time) {
        QRevokedToken qToken = QRevokedToken.revokedToken;

        query.delete(qToken)
                .where(qToken.expiredAt.before(time))
                .execute();
    }
}
