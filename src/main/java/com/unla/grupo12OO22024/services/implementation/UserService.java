package com.unla.grupo12OO22024.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.unla.grupo12OO22024.entities.UserRole;
import com.unla.grupo12OO22024.repositories.IUserRepository;
import com.unla.grupo12OO22024.repositories.IUserRoleRepository;

import jakarta.transaction.Transactional;

@ComponentScan
@Service ("userService")
public class UserService implements UserDetailsService {

    private final IUserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private final IUserRoleRepository userRoleRepository;

    public UserService(IUserRepository userRepository, IUserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.unla.grupo12OO22024.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
	}

	private User buildUser(com.unla.grupo12OO22024.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
						true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked,
						grantedAuthorities);
	}

	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(UserRole userRole: userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<>(grantedAuthorities);
	}

	//Guarda el usuario en la base de datos
	@Transactional
    public void saveUserWithRole(com.unla.grupo12OO22024.entities.User user, String role) {
		//seteo la contraseña llamando al encoder (bcrypt)
        user.setPassword(passwordEncoder.encode(user.getPassword()));
		//seteo el estado en true (habilitado)
		user.setEnabled(true);
		//salvo el user
        userRepository.save(user);

		//creo un entitie de user role 
        UserRole userRole = new UserRole();
		//seteo el rol que eligio el usuario
        userRole.setRole(role);
		//seteo el user (foreign key en la bdd)
        userRole.setUser(user);
		//salvo el registro
        userRoleRepository.save(userRole);
    }

	//recibe un nickname y devuelve la entidad completa con ese nombre
    public com.unla.grupo12OO22024.entities.User traerPorNombre(String username) {
        com.unla.grupo12OO22024.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return user;
    }

	//recibe un id y retorna la entitie con ese id
	//aclaracion, el id de user y de user_role siempre es el mismo 
	public Optional<UserRole> traerUserRole(Long id) {
		Optional<UserRole> userRole = userRoleRepository.findById(id);
		return userRole;
	}
	
}