package com.dvgiang.electricitybillingsystem.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public class BaseRepository {
    protected EntityManager entityManager;
    protected JPAQueryFactory query;

    public BaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.query = new JPAQueryFactory(entityManager);
    }
}
