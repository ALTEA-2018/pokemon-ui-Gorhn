package com.miage.altea.tp.pokemon_ui.pokemons.service;

import java.util.List;

import com.miage.altea.tp.pokemon_ui.pokemons.vo.PokemonView;

public interface PokemonService {

    List<PokemonView> listPokemons();
    PokemonView getPokemonType(int pokedexId);

}