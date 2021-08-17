package com.cosmorbit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
public class heroesController {

	@GetMapping()
	public String getAllHeroes() {
		return "Hello World";
	}
	
	@GetMapping("/best")
	public String getBestHero() {
		return "Batman";
	}	

}
