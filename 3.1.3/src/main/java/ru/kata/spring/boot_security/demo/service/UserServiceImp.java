package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    UserRepository userRepository;
//    @Autowired
//    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    @Override
    public boolean addUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
//        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long userId, User updateUser) {
        User userToBeUpdate = userRepository.getById(userId);
        userToBeUpdate.setUsername(updateUser.getUsername());
        userToBeUpdate.setPassword(bCryptPasswordEncoder.encode(updateUser.getPassword()));
        userToBeUpdate.setRoles((Set<Role>) updateUser.getRoles());
        userRepository.save(userToBeUpdate);
//        userToBeUpdate.setPassword(bCryptPasswordEncoder.encode(updateUser.getPassword()));
//        userToBeUpdate.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
    }
}
