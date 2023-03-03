package com.cinema.moviessecuritydockerspring.domain.user;

import com.cinema.moviessecuritydockerspring.domain.role.Role;
import com.cinema.moviessecuritydockerspring.domain.role.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Checks if getAllUsers returns same user name and email as user name and email saved to database
     * via user repository save method and
     */
    @Test
    void getAllUsers() {
        Role role = getRole();
        Set<Role> roles = getRoles();
        User user = getUser(roles);
        String userName = user.getName();
        String userEmail = user.getEmail();

        List<UserResponse> users = userService.getAllUsers();
        for (UserResponse userResponse : users) {
            if (userName.equals(userResponse.getName()) && userEmail.equals(userResponse.getEmail())) {
                assertTrue(true);
            }
        }

        deleteUser(user);
        deleteRole(role);
    }


    /**
     * Tests equality between user name and email of user saved to database via repository save method
     * and user name and email returned via getUserByUsername method.
     */
    @Test
    void getUserByUsername() {
        Role role = getRole();
        Set<Role> roles = getRoles();
        User user = getUser(roles);
        String username = user.getUsername();
        String userName = user.getName();
        String userEmail = user.getEmail();

        String name = userService.getUserByUsername(username).getName();
        String email = userService.getUserByUsername(username).getEmail();

        assertEquals(userName, name);
        assertEquals(userEmail, email);

        deleteUser(user);
        deleteRole(role);
    }

    @Test
    void deleteUserByUsername() {
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