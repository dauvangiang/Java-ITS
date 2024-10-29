package com.dvgiang.electricitybillingsystem.repository.role;

import com.dvgiang.electricitybillingsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryCustom {
}
