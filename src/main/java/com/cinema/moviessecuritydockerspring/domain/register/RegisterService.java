package com.cinema.moviessecuritydockerspring.domain.register;

import com.cinema.moviessecuritydockerspring.domain.role.Role;
import com.cinema.moviessecuritydockerspring.domain.role.RoleRepository;
import com.cinema.moviessecuritydockerspring.domain.user.User;
import com.cinema.moviessecuritydockerspring.domain.user.UserMapper;
import com.cinema.moviessecuritydockerspring.domain.user.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegisterService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        User user = userMapper.toEntity(request);

        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_USER");
        roles.add(role);
        newUser.setRoles(roles);

        userRepository.save(newUser);
    }
}
