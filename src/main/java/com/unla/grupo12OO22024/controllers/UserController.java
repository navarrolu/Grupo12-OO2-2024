package com.unla.grupo12OO22024.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unla.grupo12OO22024.entities.UserRole;
import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.services.implementation.UserService;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    
    public UserController (UserService userService){
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(name="error", required=false) String error,
                        @RequestParam(name="logout", required=false) String logout) {
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return ViewRouteHelper.USER_LOGIN;
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return ViewRouteHelper.USER_LOGOUT;
    }

    @GetMapping("/loginsuccess")
    public String loginCheck() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        //traigo el user en forma de entitie
        com.unla.grupo12OO22024.entities.User user =  userService.traerPorNombre(username);
        //traigo el user_role en forma de entitie
        Optional<UserRole> userRoleOpcional = userService.traerUserRole(user.getId());
        UserRole userRole =  userRoleOpcional.get();

        if (userRole.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
            return ViewRouteHelper.ROUTE_INDEX; // Redirecciona a la vista para administradores
        } else {
            return  ViewRouteHelper.ROUTE_USER; // Redirecciona a la vista para usuarios regulares
        }
    }

}
