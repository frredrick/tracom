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

import com.fredrick.tracom.models.Devices;
import com.fredrick.tracom.services.DevicesService;

@RestController
public class DeviceController {
	@Autowired
	DevicesService devicesService;

	
	@GetMapping("/devices")
	public List<Devices> getAllDevices() {
		return devicesService.getAllDevices();
	}
	@GetMapping("/devices/{id}")
	public Optional<Devices> getAllDevices(@PathVariable(name="id") Long id) {
		return devicesService.getAllDevices(id);
	}
	
	@PostMapping("/devices")
	public ResponseEntity<String> addDevice(@RequestBody Devices device) {
		return devicesService.addDevice(device);
	}
	
	@PutMapping("/devices")
	public ResponseEntity<String> updateDevice(@RequestBody Devices device) {
		return devicesService.updateDevice(device);
	}
	@DeleteMapping("/devices/{id}")
	public  ResponseEntity<String> deleteDevice(@PathVariable(name="id") Long id) {
		return devicesService.deleteDevice(id);
	}
}
