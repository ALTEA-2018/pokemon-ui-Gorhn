package com.miage.altea.tp.pokemon_ui.pokemons.vo;

import com.miage.altea.tp.pokemon_ui.pokemons.bo.PokemonType;

public class PokemonView {
	
	private PokemonType type;
	private int level;
	
	public PokemonView() { }
	
	public PokemonView(PokemonType pokemonType) {
		this.type = pokemonType;
	}
	
	public PokemonType getType() { return type; }
	public void setType(PokemonType type) { this.type = type; }
	public int getLevel() { return level; }
	public void setLevel(int level) { this.level = level; }

}
