package com.miage.altea.tp.pokemon_ui.trainers.bo;

import java.util.List;

import com.miage.altea.tp.pokemon_ui.pokemons.bo.Pokemon;

public class Trainer {
	
	private String name;
	private List<Pokemon> team;

	public Trainer() { }
	
	public Trainer(String name) { 
		this.name = name; 
	}
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public List<Pokemon> getTeam() { return team; }
	public void setTeam(List<Pokemon> team) { this.team = team; }	
	
}
