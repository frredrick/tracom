package com.fredrick.tracom.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Devices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String model;
	String country;
	String branch;

	public Devices() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Devices(String model, String country, String branch) {
		super();
		this.id = -1;
		this.model = model;
		this.country = country;
		this.branch = branch;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

}
