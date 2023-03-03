package com.cinema.moviessecuritydockerspring.domain.role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleControllerTest {

    @Autowired
    private RoleController roleController;

    @Autowired
    private RoleRepository roleRepository;
    private String roleName;

    /**
     * Tests equality between the hard coded role name saved to the database via addNewRole method and the role
     * name requested from the database via repository findByName method.
     */
    @Test
    void addNewRole() {
        Role role = getRole();
        roleName = role.getName();

        roleController.addNewRole(roleName);

        Role byName = roleRepository.findByName(roleName);
        String name = byName.getName();

        assertEquals(roleName, name);

        deleteRole(byName);
    }


    /**
     * Tests whether the hard coded role saved to the database via save method asserts null after
     * using deleteRoleByName method.
     */
    @Test
    void deleteRoleByName() {
        Role role = getRole();
        String roleName = role.getName();
        saveRole(role);

        roleController.deleteRoleByName(roleName);

        Role byName = roleRepository.findByName(roleName);

        assertNull(byName);
    }

    /**
     * Hard coded role entity.
     */
    private static Role getRole() {
        Role role = new Role();
        role.setName("ROLE_Roll");
        return role;
    }

    private void saveRole(Role role) {
        roleRepository.save(role);
    }

    private void deleteRole(Role role) {
        roleRepository.delete(role);
    }
}