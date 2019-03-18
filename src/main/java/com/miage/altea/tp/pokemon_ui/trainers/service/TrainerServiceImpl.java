package com.miage.altea.tp.pokemon_ui.trainers.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemons.service.PokemonService;
import com.miage.altea.tp.pokemon_ui.pokemons.vo.PokemonView;
import com.miage.altea.tp.pokemon_ui.trainers.bo.Trainer;
import com.miage.altea.tp.pokemon_ui.trainers.vo.TrainerView;

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
	@Qualifier("trainerApiRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	@Value("${trainer.service.url}")
    public void setPokemonTypeServiceUrl(String trainerServiceUrl) {
        this.trainerServiceUrl = trainerServiceUrl;
    }
	
	private TrainerView trainerToView(Trainer trainer) {
		TrainerView trainerView = new TrainerView();
    	trainerView.setName(trainer.getName());
    	trainerView.setPassword(trainer.getPassword());
    	trainer.getTeam().stream().forEach(pokemon -> { 
    		PokemonView pokemonView = pokemonService.getPokemonType(pokemon.getPokedexId());
    		pokemonView.setLevel(pokemon.getLevel());
    		trainerView.addToTeam(pokemonView);
    	});
    	return trainerView;
	}
	
	@Override
	public List<TrainerView> getAllTrainers() {
		return getOtherTrainers("");
	}
	
	@Override
	public List<TrainerView> getOtherTrainers(String trainerName) {
		Trainer[] objects = restTemplate.getForObject(trainerServiceUrl+"/trainers/", Trainer[].class);
        List<Trainer> trainers = Arrays.asList(objects).stream()
        		.sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
        		.filter(trainer -> trainerName == null || "".equals(trainerName) ? 
        				true : 
        				!trainer.getName().equals(trainerName))
        		.collect(Collectors.toList());
        List<TrainerView> result = trainers.stream()
        		.map(trainer -> trainerToView(trainer))
        		.collect(Collectors.toList());
        return result;
	}

	@Override
	public TrainerView getByName(String trainerName) {
		Trainer object = restTemplate.getForObject(trainerServiceUrl+"/trainers/"+trainerName, Trainer.class);
		if (object != null) {
			return trainerToView(object);
		}
		return null;
	}
	
}
