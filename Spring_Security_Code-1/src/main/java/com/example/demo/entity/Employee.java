package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name is Mandatory")
	@Pattern(regexp = "^[a-zA-Z]+$",message ="name Should Consist Letters")
	private String name;
	private String departmentname;
	@NotBlank(message = "Provide Role For User")
	private String role;
	@Email(message = "PLease Provide a Valid Email")
	private String email;
	@NotBlank(message = "Password is Mandatory")
	private String password;
	
	public Employee() {
		super();
		
	}

	public Employee(int id, String name, String departmentname, String role, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.departmentname = departmentname;
		this.role = role;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
