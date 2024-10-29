package com.dvgiang.electricitybillingsystem.repository.token;

import java.util.Date;

public interface RevokedTokenRepositoryCustom {
    boolean existsByToken(String token);
    void deleteAllOlderThan(Date time);
}
