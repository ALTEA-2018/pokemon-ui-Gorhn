package com.miage.altea.tp.pokemon_ui.pokemons.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemons.bo.Pokemon;

@Service
public class PokemonServiceImpl implements PokemonService {

	private RestTemplate restTemplate;
	private String pokemonServiceUrl;
	
	public List<Pokemon> listPokemons() {
        Pokemon[] objects = restTemplate.getForObject(pokemonServiceUrl+"/pokemon/", Pokemon[].class);
        return Arrays.asList(objects).stream().sorted((p1, p2) -> Integer.compare(p1.getId(), p2.getId())).collect(Collectors.toList());
    }

	@Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	@Value("${pokemon.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}
