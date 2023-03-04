package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleDao {
    Role addRole(Role role);
    Set<Role> getAllRoles();

    Optional<Role> findByName(String name);
}
