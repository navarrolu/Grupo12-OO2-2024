package com.unla.grupo12OO22024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unla.grupo12OO22024.entities.User;
import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.services.implementation.UserService;

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
    public ModelAndView registerUser(@ModelAttribute("user") User user, 
                                     @RequestParam("role") String role, 
                                     RedirectAttributes redirectAttributes,
                                     BindingResult result){
        ModelAndView mV = new ModelAndView();
        if (result.hasErrors()) {
            mV.setViewName(ViewRouteHelper.USER_REGISTER);
        } else {
            userService.saveUserWithRole(user, role);
            mV.addObject("user", new User());

            if (role.equalsIgnoreCase("ROLE_ADMIN")) {
               mV.setViewName(ViewRouteHelper.ROUTE_INDEX); // Redirecciona a la vista para administradores
            } else {
                mV.setViewName(ViewRouteHelper.ROUTE_USER); // Redirecciona a la vista para usuarios regulares
            }
            if (user != null) {
                // User registration successful
                redirectAttributes.addFlashAttribute("registrationSuccess", "Usuario registrado exitosamente!"); // Add flash attribute
            }
        }
        return mV;
    }
}
