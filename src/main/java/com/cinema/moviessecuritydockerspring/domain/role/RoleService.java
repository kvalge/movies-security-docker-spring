package com.cinema.moviessecuritydockerspring.domain.role;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleRepository roleRepository;

    public void addNewRole(String name) {
        Role entity = roleMapper.toEntity(name);

        roleRepository.save(entity);
    }
}
