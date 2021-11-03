package com.fredrick.tracom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fredrick.tracom.models.Countries;

@Repository
public interface CountriesRepository extends JpaRepository<Countries, Long> {

}
