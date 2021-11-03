package com.fredrick.tracom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fredrick.tracom.models.Branches;

@Repository
public interface BranchesRepository extends JpaRepository<Branches, Long> {
	
}
