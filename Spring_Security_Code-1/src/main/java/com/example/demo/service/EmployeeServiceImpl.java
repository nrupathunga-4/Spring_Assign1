package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFound;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Employee saveEmployee(Employee employee) {
		
		employee.setPassword(encoder.encode(employee.getPassword()));
		 return employeeRepository.save(employee);
		
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
	public void deleteEmployee(int id) throws EmployeeNotFound {
		employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFound("Employee Doesn't Exist in Database"));
		employeeRepository.deleteById(id);
	}

	@Override
	public Employee updatePassword(Employee employee, String email) throws EmployeeNotFound {
		Employee employee2=employeeRepository.findByEmail(email);
		if(email!=null)
		{
			employee2.setPassword(encoder.encode(employee.getPassword()));
		}
		else
		{
			throw new EmployeeNotFound("Employee Doesn't Exist In Database");
		}
		return employeeRepository.save(employee2);
	}

}
