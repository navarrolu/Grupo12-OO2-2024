package com.unla.grupo12OO22024.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


@Service ("userService")
public class UserService implements UserDetailsService {

    private IUserRepository userRepository;
	private BCryptPasswordEncoder passwordEncoder;
	private IUserRoleRepository userRoleRepository;

    public UserService(IUserRepository userRepository, IUserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = passwordEncoder;
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

    /*public com.unla.grupo12OO22024.entities.User saveUser( com.unla.grupo12OO22024.entities.User user, String role) {
        user.setPassword(user.getPassword());
        user.setEnabled(true);  //habilitar el usuario
		UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
        return userRepository.save(user);
    } //revisar*/

	@Transactional
    public void saveUserWithRole(com.unla.grupo12OO22024.entities.User user, String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
		//user.setPassword(user.getPassword());
		user.setEnabled(true);
        userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        userRoleRepository.save(userRole);
    }

	
}