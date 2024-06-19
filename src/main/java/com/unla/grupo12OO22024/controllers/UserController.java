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


    //vista del login
    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(name="error", required=false) String error,
                        @RequestParam(name="logout", required=false) String logout) {
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return ViewRouteHelper.USER_LOGIN;
    }

    //vista logout
    @GetMapping("/logout")
    public String logout(Model model) {
        return ViewRouteHelper.USER_LOGOUT;
    }


    //Lu: 
    //Se captura el context del usuario para identificar que tipo de usuario
    //(admin o user) para saber a que redireccionarlo (vista de compras o index)
    @GetMapping("/loginsuccess")
    public String loginCheck() {
        //capturo el context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        //asigno el nombre del usuario
        String username = authentication.getName();

        //busco la entidad que tenga ese username
        com.unla.grupo12OO22024.entities.User user =  userService.traerPorNombre(username);
        
        //traigo el user_role en forma de entitie
        Optional<UserRole> userRoleOpcional = userService.traerUserRole(user.getId());
        UserRole userRole =  userRoleOpcional.get();

        //verifico si el rol de la entitie de user_role asignada al user es admin o user
        if (userRole.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
        
            return ViewRouteHelper.ROUTE_INDEX; // Redirecciona a la vista para administradores
        } else {
            return  ViewRouteHelper.ROUTE_USER; // Redirecciona a la vista para usuarios regulares
        }
    }
}
