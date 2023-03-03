package com.cinema.moviessecuritydockerspring.domain.user;

import com.cinema.moviessecuritydockerspring.domain.role.Role;
import com.cinema.moviessecuritydockerspring.domain.role.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Tests equality between the username of a hard coded user entity saved to the database and
     * the username returned via findByUsername method.
     */
    @Test
    void findByUsername() {
        Role role = getRole();
        Set<Role> roles = getRoles();
        User user = getUser(roles);
        String userUsername = user.getUsername();

        String username = userRepository.findByUsername(userUsername).getUsername();

        assertEquals(userUsername, username);

        deleteUser(user);
        deleteRole(role);
    }

    @Test
    void findByUsernameOrEmail() {
    }

    /**
     * Hard coded role entity.
     */
    private Role getRole() {
        Role role = new Role();
        role.setName("ROLE_Roll");
        return role;
    }

    private Set<Role> getRoles() {
        Set<Role> roles = new HashSet<>();
        roles.add(getRole());
        return roles;
    }

    /**
     * Hard coded user entity saved to database.
     */
    private User getUser(Set<Role> roles) {
        User user = new User();
        user.setName("Nimi");
        user.setUsername("Kasutaja");
        user.setEmail("Email");
        user.setPassword("Salasõna");
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    private void deleteUser(User user) {
        userRepository.delete(user);
    }

    private void deleteRole(Role role) {
        roleRepository.delete(role);
    }
}