package com.fredrick.tracom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fredrick.tracom.models.Devices;

@Repository
public interface DevicesRepository extends JpaRepository<Devices, Long> {

}
