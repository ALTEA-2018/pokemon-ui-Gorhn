package com.miage.altea.tp.pokemon_ui.trainers.service;

import java.util.List;

import com.miage.altea.tp.pokemon_ui.trainers.vo.TrainerView;

public interface TrainerService {

	List<TrainerView> getAllTrainers();
	List<TrainerView> getOtherTrainers(String trainerName);
	TrainerView getByName(String trainerName);
	
}
