package com.fredrick.tracom.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fredrick.tracom.models.Countries;
import com.fredrick.tracom.repositories.CountriesRepository;

@Service
public class CountriesService {

	@Autowired
	CountriesRepository countriesRepository;

	public ResponseEntity<String> addCountry(final Countries country) {

		List<Countries> allCountries = countriesRepository.findAll();
		allCountries = allCountries.stream().filter(c -> c.getCountryCode().equalsIgnoreCase(country.getCountryCode()))
				.collect(Collectors.toList());

		// validating fields
		if (country == null) {
			return new ResponseEntity<String>("Add a request body", HttpStatus.BAD_REQUEST);

		}
		if (country.getCountryCode() == null || country.getCountryCode().isEmpty()) {
			return new ResponseEntity<String>("Country Code is Empty!", HttpStatus.BAD_REQUEST);

		}

		if (country.getCountryName() == null || country.getCountryName().isEmpty()) {
			return new ResponseEntity<String>("Country Name is Empty!", HttpStatus.BAD_REQUEST);

		}
		if (country.getModifiedBy() == null || country.getModifiedBy().isEmpty()) {
			return new ResponseEntity<String>("Modified by  is Empty!", HttpStatus.BAD_REQUEST);

		}
		if (!allCountries.isEmpty()) {
			return new ResponseEntity<String>("Country Code already exist", HttpStatus.BAD_REQUEST);
		}

		// saving Countries
		if (allCountries.isEmpty()) {
			country.setDateModified(Timestamp.valueOf(LocalDateTime.now()));
			countriesRepository.save(country);
			return new ResponseEntity<String>("Country saved Successfully", HttpStatus.OK);
		}

		return new ResponseEntity<String>("failed to add", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<String> updateCountry(final Countries country) {

		// validating fields
		if (country == null) {
			return new ResponseEntity<String>("Add a request body", HttpStatus.BAD_REQUEST);

		}
		if (country.getCountryCode() == null || country.getCountryCode().isEmpty()) {
			return new ResponseEntity<String>("Country Code is Empty!", HttpStatus.BAD_REQUEST);

		}

		if (country.getCountryName() == null || country.getCountryName().isEmpty()) {
			return new ResponseEntity<String>("Country Name is Empty!", HttpStatus.BAD_REQUEST);

		}
		if (country.getModifiedBy() == null || country.getModifiedBy().isEmpty()) {
			return new ResponseEntity<String>("Modified by  is Empty!", HttpStatus.BAD_REQUEST);

		}
		if (!countriesRepository.existsById(country.getId())) {
			return new ResponseEntity<String>("Country with id : " + country.getId() + " does not exist",
					HttpStatus.NOT_FOUND);

		}
		

		Countries countryToUpdate = countriesRepository.getById(country.getId());
		countryToUpdate.setCountryName(country.getCountryName());
		countryToUpdate.setModifiedBy(country.getModifiedBy());
		countryToUpdate.setDateModified(Timestamp.valueOf(LocalDateTime.now()));
		Countries res = countriesRepository.save(countryToUpdate);
		if (res != null) {
			return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<String> deleteCountry(final Long id) {

		if (countriesRepository.existsById(id)) {
			countriesRepository.deleteById(id);
			return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("failed to delete country with id " + id + "", HttpStatus.NOT_FOUND);

	}

	public List<Countries> getAllCountries() {
		return countriesRepository.findAll();
	}

	
	public Optional<Countries> getAllCountries(Long id) {
		return countriesRepository.findById(id);
	}
	public Countries getCountry(String countryCode) {
		
		List<Countries> allCountries = countriesRepository.findAll();
		allCountries = allCountries.stream().filter(c -> c.getCountryCode()
				.equalsIgnoreCase(countryCode))
				.collect(Collectors.toList());
		if(allCountries.isEmpty()) {
			return new Countries();
		}
		
		return allCountries.get(0);
	}
}
