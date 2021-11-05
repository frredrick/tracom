package com.fredrick.tracom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fredrick.tracom.models.Branches;

@Repository
public interface BranchesRepository extends JpaRepository<Branches, Long> {
	List<Branches> findByBranchName(String name);
	List<Branches> findByBranchNameAndByModifiedBy(String name,String modifiedBy);
}
