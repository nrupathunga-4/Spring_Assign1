package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFound;
import com.example.demo.repository.EmployeeRegister;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private EmployeeRegister employeeRegister;

	@Override
	public Employee saveEmployee(Employee employee) {	
		employee.setPassword(encoder.encode(employee.getPassword()));
		 return employeeRegister.save(employee);
	}

	@Override
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) throws EmployeeNotFound {
		return employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFound("Employee Doesn't Exist in Database"));
	}


	@Override
	public Employee updatePassword(Employee employee, String email) {
		Employee employee2=employeeRepository.findByEmail(email);
		employee2.setPassword(encoder.encode(employee.getPassword()));
		return employeeRepository.save(employee2);
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepository.findById(id);
		employeeRepository.deleteById(id);
		
	}

	

}
