package com.miage.altea.tp.controller;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.miage.altea.tp.pokemon_ui.controller.PokemonController;
import com.miage.altea.tp.pokemon_ui.pokemons.bo.Pokemon;
import com.miage.altea.tp.pokemon_ui.pokemons.service.PokemonService;

public class PokemonControllerTest {
    @Test
    void controllerShouldBeAnnotated(){
        assertNotNull(PokemonController.class.getAnnotation(Controller.class));
    }

    @Test
    void pokemons_shouldReturnAModelAndView() {
        var pokemonTypeService = mock(PokemonService.class);

        when(pokemonTypeService.listPokemons()).thenReturn(List.of(new Pokemon(), new Pokemon()));

        var pokemonTypeController = new PokemonController();
        pokemonTypeController.setPokemonService(pokemonTypeService);
        var modelAndView = pokemonTypeController.pokedex();

        assertEquals("pokedex", modelAndView.getViewName());
        var pokemons = (List<Pokemon>)modelAndView.getModel().get("pokemonList");
        assertEquals(2, pokemons.size());
        verify(pokemonTypeService).listPokemons();
    }

    @Test
    void pokemons_shouldBeAnnotated() throws NoSuchMethodException {
        var pokemonsMethod = PokemonController.class.getDeclaredMethod("pokedex");
        var getMapping = pokemonsMethod.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/pokedex"}, getMapping.value());
    }


}