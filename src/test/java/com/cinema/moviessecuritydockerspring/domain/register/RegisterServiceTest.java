package com.cinema.moviessecuritydockerspring.domain.register;

import com.cinema.moviessecuritydockerspring.domain.role.Role;
import com.cinema.moviessecuritydockerspring.domain.role.RoleRepository;
import com.cinema.moviessecuritydockerspring.domain.user.User;
import com.cinema.moviessecuritydockerspring.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RegisterServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Tests whether user properties saved to database via repository save method equals to user
     * properties returned via repository findByUsername method.
     */
    @Test
    void registerNewUser() {
        User userEntity = new User();
        userEntity.setName("Nimi");
        userEntity.setUsername("Kasutaja");
        userEntity.setEmail("Email");
        userEntity.setPassword(passwordEncoder.encode("Salasõna"));

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName("ROLE_roll");
        roles.add(role);
        userEntity.setRoles(roles);

        userRepository.save(userEntity);

        User user = userRepository.findByUsername(userEntity.getUsername());

        assertEquals(userEntity.getName(), user.getName());
        assertEquals(userEntity.getUsername(), user.getUsername());
        assertEquals(userEntity.getEmail(), user.getEmail());
        assertEquals(userEntity.getPassword(), user.getPassword());

        userRepository.delete(userEntity);
        roleRepository.delete(role);
    }
}