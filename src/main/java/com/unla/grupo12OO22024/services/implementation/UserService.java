package com.unla.grupo12OO22024.services.implementation;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implementar la lógica para cargar un usuario desde la base de datos
        // Aquí se muestra un usuario en memoria para ilustración
        if ("user".equals(username)) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password("$2a$10$W1J8vHfHbfg./eJvYjeEfeY7bP.S8gWd4k9og3Upk7wOya3XB84iG") // password: "password" encriptada
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
