package com.fredrick.tracom.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Transactions {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Column(unique=true)
	String deviceSerialnumber;
	
	String bankBranch;
	
	String clientCode;
	
	String countryCode;
	
	String modifiedBy;
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ")
	Timestamp dateModified;

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transactions(long id, String deviceSerialnumber, String bankBranch, String clientCode, String countryCode,
			String modifiedBy, Timestamp dateModified) {
		super();
		this.id = id;
		this.deviceSerialnumber = deviceSerialnumber;
		this.bankBranch = bankBranch;
		this.clientCode = clientCode;
		this.countryCode = countryCode;
		this.modifiedBy = modifiedBy;
		this.dateModified = dateModified;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeviceSerialnumber() {
		return deviceSerialnumber;
	}

	public void setDeviceSerialnumber(String deviceSerialnumber) {
		this.deviceSerialnumber = deviceSerialnumber;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
