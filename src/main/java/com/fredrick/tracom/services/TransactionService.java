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

import com.fredrick.tracom.models.Branches;
import com.fredrick.tracom.models.Clients;
import com.fredrick.tracom.models.Countries;
import com.fredrick.tracom.models.Devices;
import com.fredrick.tracom.models.Transactions;
import com.fredrick.tracom.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	BranchesServices branchesServices;
	@Autowired
	ClientsService clientsService;
	@Autowired
	CountriesService countriesService;
	@Autowired
	DevicesService devicesService;

	public ResponseEntity<String> addTransaction(final Transactions transaction) {

		List<Transactions> allTransactions = transactionRepository.findAll();
		allTransactions = allTransactions.stream()
				.filter(c -> c.getDeviceSerialnumber().equalsIgnoreCase(transaction.getDeviceSerialnumber()))
				.collect(Collectors.toList());

		// validating fields
		if (transaction == null) {
			return new ResponseEntity<String>("Add a request body", HttpStatus.BAD_REQUEST);

		}
		if (transaction.getBankBranch() == null || transaction.getBankBranch().isEmpty()) {
			return new ResponseEntity<String>("Bank branch  is Empty!", HttpStatus.BAD_REQUEST);

		}

		Branches validateBranch = branchesServices.getSingleBranch(transaction.getBankBranch());
		if (validateBranch.getBranchName() == null || validateBranch.getBranchName().isEmpty()) {

			return new ResponseEntity<String>("Bank branch  is not registered Please Add!", HttpStatus.BAD_REQUEST);
		}

		if (transaction.getCountryCode() == null || transaction.getCountryCode().isEmpty()) {
			return new ResponseEntity<String>("Country  is Empty!", HttpStatus.BAD_REQUEST);

		}
		Countries validateCountry = countriesService.getCountry(transaction.getCountryCode());
		if (validateCountry.getCountryName() == null || validateCountry.getCountryName().isEmpty()) {
			return new ResponseEntity<String>("Country Code  is not registered please add !", HttpStatus.BAD_REQUEST);
		}

		if (transaction.getDeviceSerialnumber() == null || transaction.getDeviceSerialnumber().isEmpty()) {
			return new ResponseEntity<String>(" Device Serial Number  is Empty!", HttpStatus.BAD_REQUEST);

		}

		Devices validateDevice = devicesService.getDevices(transaction.getDeviceSerialnumber());
		if (validateDevice.getSerialNumber() == null || validateDevice.getSerialNumber().isEmpty()) {
			return new ResponseEntity<String>(" Device Serial is not yet registered please add!", HttpStatus.NOT_FOUND);
		}

		if (transaction.getClientCode() == null || transaction.getClientCode().isEmpty()) {
			return new ResponseEntity<String>("Client Code   is Empty!", HttpStatus.BAD_REQUEST);

		}
		Clients validateClient = clientsService.getClient(transaction.getClientCode());
		if (validateClient.getClientCode() == null || validateClient.getClientCode().isEmpty()) {
			return new ResponseEntity<String>("Client not registered  please add a registered client!", HttpStatus.NOT_FOUND);

		}
		if (transaction.getModifiedBy() == null || transaction.getModifiedBy().isEmpty()) {
			return new ResponseEntity<String>("Modified by  is Empty!", HttpStatus.BAD_REQUEST);

		}
		if (!allTransactions.isEmpty()) {
			return new ResponseEntity<String>("device already assigned", HttpStatus.CONFLICT);
		}

		// saving Device
		if (allTransactions.isEmpty()) {
			transaction.setDateModified(Timestamp.valueOf(LocalDateTime.now()));
			transactionRepository.save(transaction);
			return new ResponseEntity<String>("Device saved Successfully", HttpStatus.OK);
		}

		return new ResponseEntity<String>("failed to add", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<String> updateTransaction(final Transactions transaction) {
		// validating fields
		if (transaction == null) {
			return new ResponseEntity<String>("Add a request body", HttpStatus.BAD_REQUEST);

		}
		if (transaction.getBankBranch() == null || transaction.getBankBranch().isEmpty()) {
			return new ResponseEntity<String>("Bank branch  is Empty!", HttpStatus.BAD_REQUEST);

		}

		Branches validateBranch = branchesServices.getSingleBranch(transaction.getBankBranch());
		if (validateBranch.getBranchName() == null || validateBranch.getBranchName().isEmpty()) {

			return new ResponseEntity<String>("Bank branch  is not registered Please Add!", HttpStatus.NOT_FOUND);
		}

		if (transaction.getCountryCode() == null || transaction.getCountryCode().isEmpty()) {
			return new ResponseEntity<String>("Country  is Empty!", HttpStatus.BAD_REQUEST);

		}
		Countries validateCountry = countriesService.getCountry(transaction.getCountryCode());
		if (validateCountry.getCountryName() == null || validateCountry.getCountryName().isEmpty()) {
			return new ResponseEntity<String>("Country Code  is not registered please add !", HttpStatus.NOT_FOUND);
		}

		if (transaction.getDeviceSerialnumber() == null || transaction.getDeviceSerialnumber().isEmpty()) {
			return new ResponseEntity<String>(" Device Serial Number  is Empty!", HttpStatus.BAD_REQUEST);

		}

		Devices validateDevice = devicesService.getDevices(transaction.getDeviceSerialnumber());
		if (validateDevice.getSerialNumber() == null || validateDevice.getSerialNumber().isEmpty()) {
			return new ResponseEntity<String>(" Device Serial is not yet registered please add!", HttpStatus.NOT_FOUND);
		}

		if (transaction.getClientCode() == null || transaction.getClientCode().isEmpty()) {
			return new ResponseEntity<String>("Client Code   is Empty!", HttpStatus.BAD_REQUEST);

		}
		Clients validateClient = clientsService.getClient(transaction.getClientCode());
		if (validateClient.getClientCode() == null || validateClient.getClientCode().isEmpty()) {
			return new ResponseEntity<String>("Client not registered  please add a registered client!", HttpStatus.NOT_FOUND);

		}
		if (transaction.getModifiedBy() == null || transaction.getModifiedBy().isEmpty()) {
			return new ResponseEntity<String>("Modified by  is Empty!", HttpStatus.BAD_REQUEST);

		}
		if (!transactionRepository.existsById(transaction.getId())) {
			return new ResponseEntity<String>("Transaction with Id : " + transaction.getId() + " does not exists",
					HttpStatus.NOT_FOUND);

		}

		Transactions transactionsToUpdate = transactionRepository.getById(transaction.getId());
		transactionsToUpdate.setBankBranch(transaction.getBankBranch());
		transactionsToUpdate.setClientCode(transaction.getClientCode());
		transactionsToUpdate.setCountryCode(transaction.getCountryCode());
		transactionsToUpdate.setDeviceSerialnumber(transaction.getDeviceSerialnumber());
		transactionsToUpdate.setModifiedBy(transaction.getModifiedBy());
		Transactions res = transactionRepository.save(transactionsToUpdate);
		if (res != null) {
			return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<String> deleteTransaction(final Long id) {

		if (transactionRepository.existsById(id)) {
			transactionRepository.deleteById(null);
			return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("failed to delete transaction with id :" + id + "", HttpStatus.NOT_FOUND);

	}

	public List<Transactions> getllTransactions() {
		return transactionRepository.findAll();
	}

	public Optional<Transactions> getllTransactions(Long id) {
		return transactionRepository.findById(id);
	}
}
