package com.webapp.craictivity.controller;

import com.webapp.craictivity.entity.User;
import com.webapp.craictivity.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private CustomUserDetailsService userService;

    @GetMapping("/users/new")
    public String createUserForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "create_user";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") User user){
        user.getParticipant().setUser(user); //link the user to the participant (add the foreign key in the database)
        userService.saveUser(user);
        return "redirect:/";
    }
}
