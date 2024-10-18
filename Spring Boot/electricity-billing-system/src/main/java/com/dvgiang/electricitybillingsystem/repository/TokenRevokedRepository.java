package com.dvgiang.electricitybillingsystem.repository;

import com.dvgiang.electricitybillingsystem.entity.TokenRevoked;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRevokedRepository extends JpaRepository<TokenRevoked, Long> {
//  int countTokenRevokedByToken(String token);

  Optional<TokenRevoked> findOneByToken(String token);

  @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TokenRevoked t WHERE t.token = :token")
  boolean existsByToken(@Param("token") String token);

  @Modifying
  @Transactional
  @Query("DELETE FROM TokenRevoked tr WHERE tr.expiredAt <= :threeHoursAgo")
  void deleteAllOlderThan(@Param("threeHoursAgo")Date threeHoursAgo);
}
