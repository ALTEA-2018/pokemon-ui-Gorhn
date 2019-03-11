package com.miage.altea.tp.pokemon_ui.pokemons.vo;

import java.util.List;

public class TrainerView {

	private String name;
	private List<PokemonView> team;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public List<PokemonView> getTeam() { return team; }
	public void setTeam(List<PokemonView> team) { this.team = team; }
	
	public void addToTeam(PokemonView pokemon) {
		team.add(pokemon);
	}
	
}
