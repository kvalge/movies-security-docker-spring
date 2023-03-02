package com.cinema.moviessecuritydockerspring.domain.role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Tests equality between the hard coded role entity name saved to the database and the role name requested
     * from the database via the findByName method.
     */
    @Test
    void findByName() {
        Role role = getRole();
        saveRole(role);
        String roleName = role.getName();

        String name = roleRepository.findByName(role.getName()).getName();
        assertEquals(roleName, name);

        deleteRole(role);
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