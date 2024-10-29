package com.dvgiang.electricitybillingsystem.repository.user;

import com.dvgiang.electricitybillingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
