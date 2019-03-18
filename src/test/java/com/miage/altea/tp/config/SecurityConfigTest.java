package com.miage.altea.tp.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.miage.altea.tp.pokemon_ui.config.SecurityConfig;
import com.miage.altea.tp.pokemon_ui.trainers.service.TrainerService;
import com.miage.altea.tp.pokemon_ui.trainers.vo.TrainerView;

class SecurityConfigTest {

    @Test
    void securityConfig_shouldExtendWebSecurityConfigurerAdapter(){
        assertTrue(WebSecurityConfigurerAdapter.class.isAssignableFrom(SecurityConfig.class));
    }

    @Test
    void passwordEncoder_shouldBeBCryptPasswordEncoder() {
        var securityConfig = new SecurityConfig();
        var passwordEncoder = securityConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }

    @Test
    void userDetailsService_shouldUseTrainerService() {
        var securityConfig = new SecurityConfig();

        var trainerService = mock(TrainerService.class);
        TrainerView trainer = new TrainerView();
        trainer.setName("Garry");
        trainer.setPassword("secret");
        when(trainerService.getByName("Garry")).thenReturn(trainer);

        securityConfig.setTrainerService(trainerService);

        var userDetailsService = securityConfig.userDetailsService();

        var garry = userDetailsService.loadUserByUsername("Garry");

        // mock should be called
        verify(trainerService).getByName("Garry");

        assertNotNull(garry);
        assertEquals("Garry", garry.getUsername());
        assertEquals("secret", garry.getPassword());
        assertTrue(garry.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    void userDetailsService_shouldThrowABadCredentialsException_whenUserDoesntExists() {
        var securityConfig = new SecurityConfig();

        // the mock returns null
        var trainerService = mock(TrainerService.class);
        securityConfig.setTrainerService(trainerService);

        var userDetailsService = securityConfig.userDetailsService();

        var exception = assertThrows(BadCredentialsException.class, () -> userDetailsService.loadUserByUsername("Garry"));
        assertEquals("No User found for that username (Garry)", exception.getMessage());

        // mock should be called
        verify(trainerService).getByName("Garry");
    }

}