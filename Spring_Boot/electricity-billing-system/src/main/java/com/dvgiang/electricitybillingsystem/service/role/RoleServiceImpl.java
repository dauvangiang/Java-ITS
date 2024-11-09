package com.dvgiang.electricitybillingsystem.service.role;

import com.dvgiang.electricitybillingsystem.entity.Role;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.mapper.role.RoleMapper;
import com.dvgiang.electricitybillingsystem.repository.role.RoleRepository;
import com.dvgiang.electricitybillingsystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseService<RoleRepository, RoleMapper> implements RoleService{
    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Role getRoleByName(String name) {
        return repository.getRoleByName(name)
                .orElseThrow(() -> new NotFoundException("Role has name = " + name + " does not exist!"));
    }

    @Override
    public Long getRoleIDByName(String name) {
        return repository.getRoleIDByName(name);
    }
}
