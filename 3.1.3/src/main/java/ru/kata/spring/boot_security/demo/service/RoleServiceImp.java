package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Role findById(long id){
        return entityManager.find(Role.class, id);
    }
    @Override
     public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r").getResultList();
     }
     @Override
     public void addRole(Role role) {
        entityManager.persist(role);
     }
     @Override
     public Role getRole(String role) {
        return entityManager.createQuery("select r from Role r where r.role =: role", Role.class)
                .setParameter("role",role).getSingleResult();
     }





}
