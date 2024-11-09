package com.dvgiang.electricitybillingsystem.service;

/*
* T1: Repository
* T2: Mapper
*/
public class BaseService<T1, T2> {
    protected T1 repository;
    protected T2 mapper;

    public BaseService(T1 repository, T2 mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
