package com.miage.altea.tp.pokemon_ui.pokemons.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemons.bo.PokemonType;
import com.miage.altea.tp.pokemon_ui.pokemons.vo.PokemonView;

@Service
public class PokemonServiceImpl implements PokemonService {

	private RestTemplate restTemplate;
	private String pokemonServiceUrl;
	
	@Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	@Value("${pokemon.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
	
	public List<PokemonView> listPokemons() {
        PokemonType[] objects = restTemplate.getForObject(pokemonServiceUrl+"/pokemon/", PokemonType[].class);
        return Arrays.asList(objects).stream().sorted((p1, p2) -> Integer.compare(p1.getId(), p2.getId())).map(pokemonType -> new PokemonView(pokemonType)).collect(Collectors.toList());
    }

	@Override
	public PokemonView getPokemonType(int pokedexId) {
		PokemonType result = restTemplate.getForObject(pokemonServiceUrl+"/pokemon/"+pokedexId, PokemonType.class);
		return new PokemonView(result);
	}

}
