package com.dvgiang.electricitybillingsystem.repository;

import com.dvgiang.electricitybillingsystem.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
//    @Query("SELECT h FROM History h WHERE h.customer.id = :customerId")
    List<History> findAllByCustomerId(@Param("customerId") Long customerId);
}
