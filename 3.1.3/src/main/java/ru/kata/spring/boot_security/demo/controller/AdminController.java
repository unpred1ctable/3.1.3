package ru.kata.spring.boot_security.demo.controller;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserDetailsService userDetailsService;
    public AdminController(UserService userService, RoleService roleService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
    }
    @GetMapping("")
    public String index(Principal principal, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("myUser", userDetailsService.loadUserByUsername(principal.getName()));
        return "admin/index";
    }

    //-----Create ------
    @GetMapping("/add")
    public String add(@ModelAttribute("user") User user, Model model, Principal principal) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("myUser", userDetailsService.loadUserByUsername(principal.getName()));
        return "admin/add";
    }
    @PostMapping("/")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/add";
        }
        userService.setUserRoles(user);
        userService.add(user);
        return "redirect:/admin";
    }

    //--------Edit---------
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, Principal principal) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("myUser", userDetailsService.loadUserByUsername(principal.getName()));
        return "admin/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "admin/edit";
        }
        userService.setUserRoles(user);
        userService.update(user);
        return "redirect:/admin";
    }

    //--------Delete-----------
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
