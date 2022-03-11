package com.example.entity;

// https://www.baeldung.com/spring-mvc-form-tutorial

public class Employee {
	
    private long id;
    private String name;
    private String contactNumber;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
    
    
    
}
