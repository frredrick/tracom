package com.fredrick.tracom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fredrick.tracom.models.Transactions;


@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
	

}
