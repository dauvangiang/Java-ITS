package com.dvgiang.electricitybillingsystem.repository.user;

import com.dvgiang.electricitybillingsystem.entity.User;

import java.util.Optional;

//Khai bao cac ham
public interface UserRepositoryCustom {
    Optional<User> getUserByUsername(String username);
}
