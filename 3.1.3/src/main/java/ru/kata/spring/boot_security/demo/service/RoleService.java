package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.Optional;
import java.util.Set;


public interface RoleService {

    Role addRole(Role role);
    Set<Role> getAllRoles();
    Optional<Role> findByName(String name);
}
