package com.fredrick.tracom.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Clients {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Column(unique=true)
	String clientCode;
	
	String clientName;
	
	String modifiedBy;
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ")
	Timestamp dateModified;

	public Clients() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	public Clients(long id, String clientCode, String clientName, String modifiedBy, Timestamp dateModified) {
		super();
		this.id = id;
		this.clientCode = clientCode;
		this.clientName = clientName;
		this.modifiedBy = modifiedBy;
		this.dateModified = dateModified;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getDateModified() {
		return dateModified;
	}

	public void setDateModified(Timestamp dateModified) {
		this.dateModified = Timestamp.valueOf(LocalDateTime.now());
	}
	

	

}
