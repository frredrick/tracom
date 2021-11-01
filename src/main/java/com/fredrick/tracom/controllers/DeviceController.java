package com.fredrick.tracom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fredrick.tracom.models.Devices;
import com.fredrick.tracom.services.DevicesService;

@RestController
public class DeviceController {
	@Autowired
	DevicesService devicesService;

	@GetMapping("/devices/test")
	public String test() {
		return "Working!";
	}
	
	@GetMapping("/devices")
	public List<Devices> getAllDevices() {
		return devicesService.getAllDevices();
	}
	
	@PostMapping("/devices")
	public ResponseEntity<Boolean> addDevice(@RequestBody Devices device) {
		return devicesService.addDevice(device);
	}
}
