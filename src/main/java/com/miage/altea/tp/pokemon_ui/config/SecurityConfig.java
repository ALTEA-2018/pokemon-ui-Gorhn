package com.miage.altea.tp.pokemon_ui.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.miage.altea.tp.pokemon_ui.trainers.service.TrainerService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private TrainerService trainerService;
	
	public void setTrainerService(TrainerService trainerService) {
		this.trainerService = trainerService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
    }
    
	@Bean
    public UserDetailsService userDetailsService() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        return username -> Optional.ofNullable(trainerService.getByName(username))
                .map(trainer -> 
                	User.withUsername(trainer.getName())
                	.password(trainer.getPassword())
                	.roles("USER")
                	.build())
                .orElseThrow(() -> 
                	new BadCredentialsException("No User found for that username ("+username+")"));
    }
	
}
