package com.cosmorbit.heroes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cosmorbit.heroes.model.Heroes;
import com.cosmorbit.heroes.repository.HeroesRepository;

@RestController
@RequestMapping("/heroes")
public class HeroesController {
	
	@Autowired
	private HeroesRepository heroesRepository;

	@GetMapping
	public List<Heroes> getAllHeroes() {
		return this.heroesRepository.findAll();
	}
	
	@GetMapping("/find")
	public Optional<Heroes> getBestHero(@RequestParam Optional<Long> id) {		
		return this.heroesRepository.findById(id.orElseGet(() -> Long.valueOf(1)));
	}	
	
	@PostMapping
	public Heroes createHero(@RequestBody Heroes heroes) {
		return this.heroesRepository.save(heroes);
	}

}
