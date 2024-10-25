package com.dvgiang.electricitybillingsystem.repository;

import com.dvgiang.electricitybillingsystem.entity.RevokedToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface RevokedTokenRepository extends JpaRepository<RevokedToken, Long> {
//  int countTokenRevokedByToken(String token);

  Optional<RevokedToken> findOneByToken(String token);

  @Query("SELECT CASE WHEN COUNT(rt) > 0 THEN true ELSE false END FROM RevokedToken rt WHERE rt.token = :token")
  boolean existsByToken(@Param("token") String token);

  @Modifying
  @Transactional
  @Query("DELETE FROM RevokedToken rt WHERE rt.expiredAt <= :time")
  void deleteAllOlderThan(@Param("time")Date time);
}
