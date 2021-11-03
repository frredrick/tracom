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

import com.fredrick.tracom.models.Branches;
import com.fredrick.tracom.services.BranchesServices;

@RestController
public class BranchesController {

	@Autowired
	BranchesServices branchService;

	@GetMapping("/branches")
	public List<Branches> getAllBranches() {
		return branchService.getAllBranches();
	}
	@GetMapping("/branches/{id}")
	public Optional<Branches> getAllBranches(@PathVariable(name="id") Long id) {
		return branchService.getAllBranches(id);
	}

	@PostMapping("/branches")
	public ResponseEntity<String> addBranch(@RequestBody Branches branch) {
		return branchService.addBranch(branch);
	}

	@PutMapping("/branches")
	public ResponseEntity<String> updateBranch(@RequestBody Branches branch) {

		return branchService.updateBranch(branch);
	}

	@DeleteMapping("/branches/{id}")
	public ResponseEntity<String> deletebranch(@PathVariable(name="id") Long id) {

		return branchService.deleteBranch(id);
	}

}
