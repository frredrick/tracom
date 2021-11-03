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

import com.fredrick.tracom.models.Clients;
import com.fredrick.tracom.repositories.ClientsRepository;

@Service
public class ClientsService {

	@Autowired
	ClientsRepository clientsRepository;

	public ResponseEntity<String> addClient(final Clients client) {

		// checking if Client exists before saving
		List<Clients> allClients = clientsRepository.findAll();
		allClients = allClients.stream().filter(c -> c.getClientCode().equalsIgnoreCase(client.getClientCode()))
				.collect(Collectors.toList());

		// validating fields
		if (client == null) {
			return new ResponseEntity<String>("Add a request body", HttpStatus.OK);

		}
		if (client.getClientCode() == null || client.getClientCode().isEmpty()) {
			return new ResponseEntity<String>("Client Code is Empty!", HttpStatus.OK);

		}

		if (client.getClientName() == null || client.getClientName().isEmpty()) {
			return new ResponseEntity<String>("Client Name is Empty!", HttpStatus.OK);

		}
		if (client.getModifiedBy() == null || client.getModifiedBy().isEmpty()) {
			return new ResponseEntity<String>("Modified by  is Empty!", HttpStatus.OK);

		}
		if (!allClients.isEmpty()) {
			return new ResponseEntity<String>("Client Code already exist", HttpStatus.OK);
		}

		// saving Clients
		if (allClients.isEmpty()) {
			client.setDateModified(Timestamp.valueOf(LocalDateTime.now()));
			clientsRepository.save(client);
			return new ResponseEntity<String>("client saved Successfully", HttpStatus.OK);
		}

		return new ResponseEntity<String>("failed to add", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<String> updateClint(final Clients client) {

		if (client == null) {
			return new ResponseEntity<String>("Add a request body", HttpStatus.OK);

		}
		if (client.getClientCode() == null || client.getClientCode().isEmpty()) {
			return new ResponseEntity<String>("Client Code is Empty!", HttpStatus.OK);

		}

		if (client.getClientName() == null || client.getClientName().isEmpty()) {
			return new ResponseEntity<String>("Client Name is Empty!", HttpStatus.OK);

		}
		if (client.getModifiedBy() == null || client.getModifiedBy().isEmpty()) {
			return new ResponseEntity<String>("Modified by  is Empty!", HttpStatus.OK);

		}
		if (!clientsRepository.existsById(client.getId())) {
			return new ResponseEntity<String>("Client with id : " + client.getId() + " does not exist", HttpStatus.OK);
		}

		Clients clientsToUpdate = clientsRepository.getById(client.getId());
		clientsToUpdate.setClientName(client.getClientName());
		clientsToUpdate.setModifiedBy(client.getModifiedBy());
		clientsToUpdate.setDateModified(Timestamp.valueOf(LocalDateTime.now()));
		Clients res = clientsRepository.save(clientsToUpdate);
		if (res != null) {
			return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("failed", HttpStatus.OK);
	}

	public ResponseEntity<String> deleteClient(final Long id) {

		if (clientsRepository.existsById(id)) {
			clientsRepository.deleteById(id);
			return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("failed to delete Client with id " + id + "", HttpStatus.OK);

	}

	public List<Clients> getAllClients() {
		return clientsRepository.findAll();
	}

	public Optional<Clients> getAllClients(Long id) {
		return clientsRepository.findById(id);
	}
	
	public Clients getClient(String clientCode) {
		List<Clients> allClients = clientsRepository.findAll();
		allClients = allClients.stream().filter(c -> c.getClientCode().equalsIgnoreCase(clientCode))
				.collect(Collectors.toList());
		if(allClients.isEmpty()) {
			return new Clients();
		}
		return allClients.get(0);
		
	}

}
