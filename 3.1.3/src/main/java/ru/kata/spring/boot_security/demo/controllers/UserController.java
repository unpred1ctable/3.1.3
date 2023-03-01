package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;

@Controller
public class UserController {

    private UserServiceImp userServiceImp;

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping(value = "/user")

    public String userPage(Model model, Principal principal) {
        model.addAttribute("userPage", userServiceImp.loadUserByUsername(principal.getName()));
        return "user";
    }

}
