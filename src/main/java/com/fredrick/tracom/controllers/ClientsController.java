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

import com.fredrick.tracom.models.Clients;
import com.fredrick.tracom.services.ClientsService;


@RestController
public class ClientsController {
	
	@Autowired
	ClientsService clientService;
	
	
	@GetMapping("/client")
	public List<Clients> getAllClients() {
		return clientService.getAllClients();
	}

	@GetMapping("/client/{id}")
	public Optional<Clients> getAllClients(@PathVariable(name="id") Long id) {
		return clientService.getAllClients(id);
	}
	
	@PostMapping("/client")
	public ResponseEntity<String> addClient(@RequestBody Clients client) {
		return clientService.addClient(client);
	}
	

	@PutMapping("/client")
	public ResponseEntity<String> updateClient(@RequestBody Clients client) {

		return clientService.updateClint(client);
	}
	
	@DeleteMapping("/client/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable(name="id") Long id) {
		return clientService.deleteClient(id);
	}
	
}
