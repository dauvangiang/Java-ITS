package com.dvgiang.electricitybillingsystem.repository.token;

import com.dvgiang.electricitybillingsystem.entity.RevokedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevokedTokenRepository extends JpaRepository<RevokedToken, Long>, RevokedTokenRepositoryCustom {
}
