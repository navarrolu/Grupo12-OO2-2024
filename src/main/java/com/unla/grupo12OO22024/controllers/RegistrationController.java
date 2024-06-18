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

    //Lu
    //Si bien en el TFI se menciona que ya no es necesaria la anotacion de autowired
    //Si la quito ya no funciona jaja
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    

    //vista del form de registro
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return ViewRouteHelper.USER_REGISTER;
    }

    //Lu:
    //Para el metodo POST traigo el parametro role en un string
    //para usarlo en el save user
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                                 @RequestParam("role") String role,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return ViewRouteHelper.USER_REGISTER;
        }

    
        //Verifico que el username que ingreso el usuario a registrarse
        //me retorne null, sino, recchazo el post
        if (userService.traerPorNombre(user.getUsername()) != null) {
            result.addError(new FieldError("user", "username", "El nombre de usuario ya existe"));
            return ViewRouteHelper.USER_REGISTER;
        }
        
        //llamo al save en userService para guardar en la bdd al user
        userService.saveUserWithRole(user, role);
    
        //junto con el equipo no encontramos la manera debido a los tiempos
        //para mostrar un mensaje en el login de registro exitoso, sin embargo
        //el registro exitoso redirije al login
        return "redirect:/login"; 
    }
}