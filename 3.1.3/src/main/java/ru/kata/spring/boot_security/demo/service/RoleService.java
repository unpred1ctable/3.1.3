package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;


public interface RoleService {
    public Role findById(long id);

    public List<Role> getAllRoles();

    public void addRole(Role role);

    public Role getRole(String role);
}
