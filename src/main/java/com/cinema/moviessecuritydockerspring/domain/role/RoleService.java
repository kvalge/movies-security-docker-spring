package com.cinema.moviessecuritydockerspring.domain.role;

import com.cinema.moviessecuritydockerspring.validation.ValidationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private ValidationService validationService;

    /**
     * Checks whether the role already exists in database before adding new role.
     */
    public void addNewRole(String name) {
        validationService.roleExists(name);

        Role entity = roleMapper.toEntity(name);

        roleRepository.save(entity);
    }

    /**
     * Checks is there a requested role in database before finding role by name for deleting.
     */
    public void deleteRoleByName(String name) {
        validationService.roleNotFound(name);

        Role role = roleRepository.findByName(name);
        roleRepository.delete(role);
    }
}
