package com.cosmorbit.heroes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@GetMapping("/findPaging")
	public ResponseEntity<Map<String, Object>> getAllHeroesByPaging(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
		
		try {
			Page<Heroes> pageHeroes = heroesRepository.findAll(PageRequest.of(page, size));
			Map<String, Object> response = new HashMap<>();
			
			response.put("heroes", pageHeroes.getContent());
			response.put("currentPage", pageHeroes.getNumber());
			response.put("totalItems", pageHeroes.getTotalElements());
			response.put("totalPages", pageHeroes.getTotalPages());
			response.put("isLastPage", pageHeroes.isLast());

			return new ResponseEntity<>(response, HttpStatus.OK);
			
			//return new ResponseEntity<>(heroesRepository.findAll(PageRequest.of(page, size)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
