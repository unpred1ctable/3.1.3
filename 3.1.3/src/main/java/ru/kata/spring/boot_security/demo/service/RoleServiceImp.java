package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {
    private RoleDao roleDao;
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    @Transactional
    @Override
    public Role addRole(Role role) {
        roleDao.addRole(role);
        return role;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Transactional
    @Override
    public Optional<Role> findByName(String name) {
        return roleDao.findByName(name);
    }
}
