package com.dvgiang.electricitybillingsystem.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public class BaseRepository<T,ID> extends SimpleJpaRepository<T,ID> {
    protected EntityManager entityManager;
    protected JPAQueryFactory query;

    public BaseRepository(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
        this.query = new JPAQueryFactory(entityManager);
    }
}
