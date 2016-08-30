package com.example;

import java.util.Date;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlElement
public class Customer {
	
	
	public Customer() {
		super();
	}
	
	public Customer(String customerName, Date birthDate) {
		super();
		this.customerName = customerName;
		this.birthDate = birthDate;
	}

	private String customerName;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	private Date birthDate;
}
