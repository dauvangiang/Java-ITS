package com.dvgiang.electricitybillingsystem.repository.user;

import com.dvgiang.electricitybillingsystem.entity.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> getUserByUsername(String username);
    Long getRoleIDByUsername(String username);
}
