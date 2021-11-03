package com.fredrick.tracom.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fredrick.tracom.models.Countries;
import com.fredrick.tracom.services.CountriesService;

@RestController
public class CountriesController {

	@Autowired
	CountriesService countryService;
	
	
	@GetMapping("/countries")
	public List<Countries> getAllCountries() {
		return countryService.getAllCountries();
	}
	@GetMapping("/countries/{id}")
	public Optional<Countries> getAllCountries(@PathVariable(name="id") Long id) {
		return countryService.getAllCountries(id);
	}
	
	@PostMapping("/countries")
	public ResponseEntity<String> addDevice(@RequestBody Countries country) {
		return countryService.addCountry(country);
	}
	
	@PutMapping("/countries")
	public ResponseEntity<String> updateCountry(@RequestBody Countries country) {
		return countryService.updateCountry(country);
	}
	
	@DeleteMapping("/countries/{id}")
	public  ResponseEntity<String> deleteCountry(@PathVariable(name="id") Long id) {
		return countryService.deleteCountry(id);
	}
	
}
