package com.unla.grupo12OO22024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unla.grupo12OO22024.entities.User;
import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.services.implementation.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return ViewRouteHelper.USER_REGISTER;
    }
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                                 @RequestParam("role") String role,
                                 BindingResult result) {

        if (result.hasErrors()) {
            // Validation errors
            return ViewRouteHelper.USER_REGISTER;
        }

        if (userService.traerPorNombre(user.getUsername()) != null) {
            // Username already exists
            result.addError(new FieldError("user", "username", "El nombre de usuario ya existe"));
            return ViewRouteHelper.USER_REGISTER;
        }

        userService.saveUserWithRole(user, role);
        // Registration successful (optionally redirect to a success page)

        return "redirect:/login"; // Redirect to login after successful registration
    }
}