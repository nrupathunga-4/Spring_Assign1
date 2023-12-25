package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFound;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);
	
	public List<Employee> getAll();
	
	public Employee getEmployeeById(int id) throws EmployeeNotFound;
	
	public void deleteEmployee(int id);
	
	public Employee updatePassword(Employee employee,String email);
	
}
