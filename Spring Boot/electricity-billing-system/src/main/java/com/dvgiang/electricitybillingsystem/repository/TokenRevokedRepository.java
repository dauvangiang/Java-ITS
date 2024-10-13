package com.dvgiang.electricitybillingsystem.repository;

import com.dvgiang.electricitybillingsystem.entity.TokenRevoked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRevokedRepository extends JpaRepository<TokenRevoked, Long> {
  int countTokenRevokedByToken(String token);
}
