package com.miage.altea.tp.pokemon_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.miage.altea.tp.pokemon_ui.trainers.service.TrainerService;

@Controller
public class TrainerController {

	@Autowired
	private TrainerService service;
	
	public void setTrainerService(TrainerService service) {
		this.service = service;
	}

	@GetMapping("/trainers")
	public ModelAndView pokedex(){
		var result = new ModelAndView("trainers");
        result.addObject("trainerList", service.getOtherTrainers());
        return result;
	}
	
}
