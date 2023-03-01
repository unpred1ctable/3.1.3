package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.UserService;



@Controller

public class AdminController {


    private UserService userService;


    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "admin")
    public String viewUsers(Model model) {
        model.addAttribute("adminPage", userService.getUsersList());
        return "users";
    }

    @GetMapping(value = "admin/new")
    public String getNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping(value = "admin/new")
    public String addNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String getFormUserUpdate(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.updateUser(id,user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/page")
    public String getUserPage(@PathVariable("id") Long id, Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("userPage", userService.getUserById(id));
        return "user";
    }

}
