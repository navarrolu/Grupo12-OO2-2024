package com.unla.grupo12OO22024.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User; //chequear linea 20
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo12OO22024.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class HomeController {

    //Lu:
    //Vista principal donde el usuario admin ingresa cuando se loguea
    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEX);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("username", user.getUsername());
        return modelAndView;
    }

    
}
