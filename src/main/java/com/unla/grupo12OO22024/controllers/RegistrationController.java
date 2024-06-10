package com.unla.grupo12OO22024.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.unla.grupo12OO22024.entities.User;
import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.services.implementation.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return ViewRouteHelper.USER_REGISTER;
    }

    /*@PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        // Usa el método buildUser para crear un nuevo usuario
        User newUser = userService.buildUser(user.getUsername(), user.getPassword());
        userService.saveUser(newUser);
        return "redirect:/login?registered";
    }*/
}
