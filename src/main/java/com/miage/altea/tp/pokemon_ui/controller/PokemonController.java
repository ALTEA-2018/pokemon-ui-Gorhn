package com.miage.altea.tp.pokemon_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.miage.altea.tp.pokemon_ui.pokemons.service.PokemonService;

@Controller
public class PokemonController {

	@Autowired
	private PokemonService service;
	
	public void setPokemonService(PokemonService service) {
		this.service = service;
	}

	@GetMapping("/pokedex")
	public ModelAndView pokedex(){
		var result = new ModelAndView("pokedex");
        result.addObject("pokemonList", service.listPokemons());
        return result;
	}

}