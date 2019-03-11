package com.miage.altea.tp.pokemon_ui.trainers.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemons.service.PokemonService;
import com.miage.altea.tp.pokemon_ui.pokemons.vo.PokemonView;
import com.miage.altea.tp.pokemon_ui.pokemons.vo.TrainerView;
import com.miage.altea.tp.pokemon_ui.trainers.bo.Trainer;

@Service
public class TrainerServiceImpl implements TrainerService {

	private RestTemplate restTemplate;
	private String trainerServiceUrl;
	
	@Autowired
	private PokemonService pokemonService;
	
	public void setPokemonService(PokemonService pokemonService) {
		this.pokemonService = pokemonService;
	}
	
	@Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	@Value("${trainer.service.url}")
    public void setPokemonTypeServiceUrl(String trainerServiceUrl) {
        this.trainerServiceUrl = trainerServiceUrl;
    }
	
	@Override
	public List<Trainer> getOtherTrainers() {
		Trainer[] objects = restTemplate.getForObject(trainerServiceUrl+"/trainers/", Trainer[].class);
        List<Trainer> trainers = Arrays.asList(objects).stream().sorted((t1, t2) -> t1.getName().compareTo(t2.getName())).collect(Collectors.toList());
        trainers.stream().map(trainer -> {
        	TrainerView trainerView = new TrainerView();
        	trainer.getTeam().stream().forEach(pokemon -> { 
        		PokemonView pokemonView = pokemonService.getPokemonType(pokemon.getPokemonType());
        		pokemonView.setLevel(pokemon.getLevel());
        		trainerView.addToTeam(pokemonView);
        	});
        	return trainerView;
        }).collect(Collectors.toList());
        return trainers;
	}
	
	

}
