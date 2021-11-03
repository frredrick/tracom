package com.fredrick.tracom.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public ResponseEntity<String> addDevice(final Devices device) {

		List<Devices> allDevices = devicesRepository.findAll();
		allDevices = allDevices.stream().filter(c -> c.getSerialNumber().equalsIgnoreCase(device.getSerialNumber()))
				.collect(Collectors.toList());

		// validating fields
		if (device == null) {
			return new ResponseEntity<String>("Add a request body", HttpStatus.OK);

		}
		
		if (device.getSerialNumber() == null || device.getSerialNumber().isEmpty()) {
			return new ResponseEntity<String>(" Device Serial Number  is Empty!", HttpStatus.OK);

		}
		if (device.getModelType() == null || device.getModelType().isEmpty()) {
			return new ResponseEntity<String>("Device Model Type   is Empty!", HttpStatus.OK);

		}
		if (device.getModifiedBy() == null || device.getModifiedBy().isEmpty()) {
			return new ResponseEntity<String>("Modified by  is Empty!", HttpStatus.OK);

		}
		if (!allDevices.isEmpty()) {
			return new ResponseEntity<String>("device already exist", HttpStatus.OK);
		}

		// saving Device
		if (allDevices.isEmpty()) {
			device.setDateModified(Timestamp.valueOf(LocalDateTime.now()));
			devicesRepository.save(device);
			return new ResponseEntity<String>("Device saved Successfully", HttpStatus.OK);
		}

		return new ResponseEntity<String>("failed to add", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<String> updateDevice(final Devices device) {
		// validating fields
		if (device == null) {
			return new ResponseEntity<String>("Add a request body", HttpStatus.OK);

		}
	
		if (device.getSerialNumber() == null || device.getSerialNumber().isEmpty()) {
			return new ResponseEntity<String>(" Device Serial Number  is Empty!", HttpStatus.OK);

		}
		if (device.getModelType() == null || device.getModelType().isEmpty()) {
			return new ResponseEntity<String>("Device Model Type   is Empty!", HttpStatus.OK);

		}
		if (device.getModifiedBy() == null || device.getModifiedBy().isEmpty()) {
			return new ResponseEntity<String>("Modified by  is Empty!", HttpStatus.OK);

		}
		if (!devicesRepository.existsById(device.getId())) {
			return new ResponseEntity<String>("Device with id :" + device.getId() + " does not exist", HttpStatus.OK);
		}
		
		

		Devices devicestoupdate = devicesRepository.getById(device.getId());
	
		devicestoupdate.setModelType(device.getModelType());
		devicestoupdate.setModifiedBy(device.getModifiedBy());
		devicestoupdate.setDateModified(Timestamp.valueOf(LocalDateTime.now()));
		Devices res = devicesRepository.save(devicestoupdate);
		if (res != null) {
			return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("failed", HttpStatus.OK);
	}

	public ResponseEntity<String> deleteDevice(final Long id) {

		if (devicesRepository.existsById(id)) {
			devicesRepository.deleteById(id);
			return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("failed to delete device with id: " + id + " ", HttpStatus.OK);

	}

	public List<Devices> getAllDevices() {
		return devicesRepository.findAll();
	}
	
	public Optional<Devices> getAllDevices(Long id) {
		return devicesRepository.findById(id);
	}
	
	public Devices getDevices(String serialnumber) {
		List<Devices> allDevices = devicesRepository.findAll();
		allDevices = allDevices.stream().filter(c -> c.getSerialNumber().
				equalsIgnoreCase(serialnumber))
				.collect(Collectors.toList());
		if(allDevices.isEmpty()) {
			return new Devices();
		}
		
		return allDevices.get(0);
	}
}
