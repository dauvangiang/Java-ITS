package com.dvgiang.electricitybillingsystem.repository;

import com.dvgiang.electricitybillingsystem.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
}
