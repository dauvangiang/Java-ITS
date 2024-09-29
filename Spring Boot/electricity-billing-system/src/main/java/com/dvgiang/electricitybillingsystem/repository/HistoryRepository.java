package com.dvgiang.electricitybillingsystem.repository;

import com.dvgiang.electricitybillingsystem.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    //Lấy danh sách lịch sử theo mã KH
    List<History> findAllByCustomerId(Long customerId);
}
