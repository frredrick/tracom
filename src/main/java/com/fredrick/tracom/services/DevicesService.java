package com.fredrick.tracom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fredrick.tracom.models.Devices;
import com.fredrick.tracom.repositories.DevicesRepository;

@Service
public class DevicesService {
	
	@Autowired
	DevicesRepository devicesRepository;

	public ResponseEntity<Boolean> addDevice(final Devices device) {
		Devices res = devicesRepository.save(device);
		if (res != null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public List<Devices> getAllDevices() {
		return devicesRepository.findAll();
	}
}
