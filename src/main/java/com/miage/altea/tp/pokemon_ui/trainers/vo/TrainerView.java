package com.miage.altea.tp.pokemon_ui.trainers.vo;

import java.util.ArrayList;
import java.util.List;

import com.miage.altea.tp.pokemon_ui.pokemons.vo.PokemonView;

public class TrainerView {

	private String name;
	private String password;
	private List<PokemonView> team;
	
	public TrainerView() {
		team = new ArrayList<PokemonView>();
	}
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public List<PokemonView> getTeam() { return team; }
	public void setTeam(List<PokemonView> team) { this.team = team; }
	
	public void addToTeam(PokemonView pokemon) {
		team.add(pokemon);
	}

}
