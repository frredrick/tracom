package com.fredrick.tracom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fredrick.tracom.models.Clients;

@Repository
public interface ClientsRepository extends JpaRepository<Clients,Long> {
	

}
