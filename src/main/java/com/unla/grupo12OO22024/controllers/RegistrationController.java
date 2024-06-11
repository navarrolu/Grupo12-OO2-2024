package com.unla.grupo12OO22024.controllers;





import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo12OO22024.entities.User;
import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.services.implementation.UserService;

@Controller
public class RegistrationController {

    
    private UserService userService;

    /*@GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return ViewRouteHelper.USER_REGISTER;
    }*/
    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute("user") User user){
        ModelAndView mV = new ModelAndView();
        mV.setViewName(ViewRouteHelper.USER_REGISTER);
        mV.addObject("user", user);
        return mV;
    }
}
