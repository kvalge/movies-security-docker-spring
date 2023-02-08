package com.cinema.moviessecuritydockerspring.domain.role;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping("/new")
    public void addNewRole(@RequestParam String name) {
        roleService.addNewRole(name);
    }

    @DeleteMapping("/name")
    public void deleteRoleByName(@RequestParam String name) {
        roleService.deleteRoleByName(name);
    }
}
