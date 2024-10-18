package com.dvgiang.electricitybillingsystem.repository;

import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElectricityPricesRepository extends JpaRepository<ElectricityPrices, Long> {
    @Query("SELECT ep FROM ElectricityPrices ep WHERE ep.id = :id AND ep.status = 1")
    Optional<ElectricityPrices> findByIdWithStatus(@Param("id") Long id);

    @Query("SELECT ep FROM ElectricityPrices ep WHERE ep.status = 1 ORDER BY ep.price ASC")
    List<ElectricityPrices> findAllWithStatusOrderByASC();

    @Modifying
    @Transactional
    @Query("UPDATE ElectricityPrices ep SET ep.status = 0 WHERE ep.id = :id")
    void deletePricesById(@Param("id") Long id);
}
