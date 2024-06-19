package com.unla.grupo12OO22024.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.unla.grupo12OO22024.services.implementation.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@ComponentScan
public class SecurityConfiguration {

    private final UserService userService;

	public SecurityConfiguration(UserService userService) {
		this.userService = userService;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/css/**", "/assets/*", "/webjars/**", "/assets/**", "/static/", "/static/*", "/register",
							"/register/*", "/resources/**", "/css/*", "/js/**", "/css/*").permitAll(); // Agregar "/register" aquí
					auth.anyRequest().authenticated();
				})
				.formLogin(login -> {
					login.loginPage("/login");
					login.loginProcessingUrl("/loginprocess");
					login.usernameParameter("username");
					login.passwordParameter("password");
					login.defaultSuccessUrl("/loginsuccess");
					login.permitAll();
				})
				.logout(logout -> {
					logout.logoutUrl("/logout");
					logout.logoutSuccessUrl("/login");
					logout.permitAll();
				})
				.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userService);
		return provider;
	}

	//Lucia:
	//metodo que eencripta la contraseña al momento de registrarse 
	//antes de guardarla en la base de datos. es llamdada userService
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


}
