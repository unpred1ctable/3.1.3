package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {


    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public User getUserById(Long userId);

    public List<User> getUsersList();

    public boolean addUser(User user);

    public void deleteUser(Long id);

    public void updateUser(Long userId, User updateUser);
}