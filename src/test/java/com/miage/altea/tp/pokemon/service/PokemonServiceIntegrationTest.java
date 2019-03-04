package com.miage.altea.tp.pokemon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.PokemonUI;
import com.miage.altea.tp.pokemon_ui.pokemons.service.PokemonService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = PokemonUI.class)
public class PokemonServiceIntegrationTest {

    @Autowired
    PokemonService service;

    @Autowired
    RestTemplate restTemplate;

    @Test
    void serviceAndTemplateShouldNotBeNull(){
        assertNotNull(service);
        assertNotNull(restTemplate);
    }

}