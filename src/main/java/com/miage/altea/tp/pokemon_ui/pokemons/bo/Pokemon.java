package com.miage.altea.tp.pokemon_ui.pokemons.bo;

public class Pokemon {
	
	private int id;
	private int pokedexId; 
	private int level; 
	
	public Pokemon() { }
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public int getPokemonType() { return pokedexId; }
	public void setPokemonType(int pokedexId) { this.pokedexId = pokedexId; }
	public int getLevel() { return level; }
	public void setLevel(int level) { this.level = level; }

}
