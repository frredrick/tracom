package com.fredrick.tracom.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import com.fredrick.tracom.models.Branches;
import com.fredrick.tracom.repositories.BranchesRepository;

@Service
public class BranchesServices {

	@Autowired
	BranchesRepository branchesRepository;

	public ResponseEntity<String> addBranch(final Branches branches) {

		// checking if branch exists before saving
		List<Branches> allBrances = branchesRepository.findAll();
		allBrances = allBrances.stream().filter(c -> c.getBranchCode().equalsIgnoreCase(branches.getBranchCode()))
				.collect(Collectors.toList());

		// validating fields
		if (branches == null) {
			return new ResponseEntity<String>("Add a request body", HttpStatus.OK);

		}
		if (branches.getBranchCode() == null || branches.getBranchCode().isEmpty()) {
			return new ResponseEntity<String>("Branch Code is Empty!", HttpStatus.OK);

		}

		if (branches.getBranchName() == null || branches.getBranchName().isEmpty()) {
			return new ResponseEntity<String>("Branch Name is Empty!", HttpStatus.OK);

		}
		if (branches.getModifiedBy() == null || branches.getModifiedBy().isEmpty()) {
			return new ResponseEntity<String>("Modified by  is Empty!", HttpStatus.OK);

		}
		if (!allBrances.isEmpty()) {
			return new ResponseEntity<String>("Branch Code already exist", HttpStatus.OK);
		}

		// saving branches
		if (allBrances.isEmpty()) {
			branches.setDateModified(Timestamp.valueOf(LocalDateTime.now()));
			branchesRepository.save(branches);
			return new ResponseEntity<String>("Branch saved Successfully", HttpStatus.OK);
		}

		return new ResponseEntity<String>("failed to add", HttpStatus.INTERNAL_SERVER_ERROR);

	}

	public ResponseEntity<String> updateBranch(final Branches branches) {

		if (branches == null) {
			return new ResponseEntity<String>("Add a request body", HttpStatus.OK);

		}
		if (branches.getBranchCode() == null || branches.getBranchCode().isEmpty()) {
			return new ResponseEntity<String>("Branch Code is Empty!", HttpStatus.OK);

		}

		if (branches.getBranchName() == null || branches.getBranchName().isEmpty()) {
			return new ResponseEntity<String>("Branch Name is Empty!", HttpStatus.OK);

		}
		if (branches.getModifiedBy() == null || branches.getModifiedBy().isEmpty()) {
			return new ResponseEntity<String>("Modified by  is Empty!", HttpStatus.OK);

		}
		if (!branchesRepository.existsById(branches.getId())) {
			return new ResponseEntity<String>("Branch with id " + branches.getId() + " does not exist", HttpStatus.OK);
		}

		Branches branchToUpdate = branchesRepository.getById(branches.getId());
		branchToUpdate.setBranchName(branches.getBranchName());
		branchToUpdate.setModifiedBy(branches.getModifiedBy());
		branchToUpdate.setDateModified(Timestamp.valueOf(LocalDateTime.now()));
		Branches res = branchesRepository.save(branchToUpdate);
		if (res != null) {
			return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("failed", HttpStatus.OK);
	}

	public ResponseEntity<String> deleteBranch(final Long id) {

		if (branchesRepository.existsById(id)) {
			branchesRepository.deleteById(id);
			return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("failed to delete Branch with id " + id + "", HttpStatus.OK);

	}

	public List<Branches> getAllBranches() {
		return branchesRepository.findAll();
	}

	public Optional<Branches> getAllBranches(Long id) {
		return branchesRepository.findById(id);
	}
	
	public Branches getSingleBranch(String branchCode) {
		List<Branches> allBrances = branchesRepository.findAll();
		allBrances = allBrances.stream().filter(c -> c.getBranchCode().equalsIgnoreCase(branchCode))
				.collect(Collectors.toList());
		if(allBrances.isEmpty()) {
			return new Branches();
		} 
		return allBrances.get(0);
		
	}

}
