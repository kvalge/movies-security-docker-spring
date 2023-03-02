package com.cinema.moviessecuritydockerspring.domain.role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Tests equality between the hard coded role name saved to the database via addNewRole method and the role
     * name requested from the database via repository findByName method.
     */
    @Test
    void addNewRole() {
        Role role = getRole();
        String roleName = role.getName();

        roleService.addNewRole(roleName);

        Role byName = roleRepository.findByName(roleName);
        String name = byName.getName();

        assertEquals(roleName, name);

        deleteRole(byName);
    }

    @Test
    void deleteRoleByName() {
    }

    /**
     * Hard coded role entity.
     */
    private static Role getRole() {
        Role role = new Role();
        role.setName("ROLE_Roll");
        return role;
    }

    private void deleteRole(Role role) {
        roleRepository.delete(role);
    }
}