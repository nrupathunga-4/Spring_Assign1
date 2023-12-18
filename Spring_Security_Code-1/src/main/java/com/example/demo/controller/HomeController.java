package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFound;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class HomeController {
	
	@Autowired
	private EmployeeService employeeService;
	

	@GetMapping("/employee/index")
	public String index()
	{
		return "<h1>index Page</h1>";
	}
	
	
	@GetMapping("/employee/home")
	public String home()
	{
		return "<h1>Home Page</h1>";
	}
	
	
	@GetMapping("/employee/about")
	public String about()
	{
		return "<h1>About Page</h1>";
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee employee)
	{
		return ResponseHandler.responsebuilder("sucess",HttpStatus.OK, employeeService.saveEmployee(employee));
	}
	
	@GetMapping("/admin/getall")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Employee> getAll()
	{
		return employeeService.getAll();
	}
	
	@GetMapping("/user/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Employee getEmployeeById(@PathVariable  int id) throws EmployeeNotFound
	{
		return employeeService.getEmployeeById(id);
	}
	
	@DeleteMapping("/admin/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> deleteEmployee(@PathVariable  int id) throws EmployeeNotFound
	{
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee is Deleted From Database",HttpStatus.OK);
	}
	
	@PutMapping("/user/{email}")
	public ResponseEntity<Employee> updatePassword(@RequestBody Employee employee,@PathVariable String email) throws EmployeeNotFound
	{
		return new ResponseEntity<Employee>(employeeService.updatePassword(employee, email),HttpStatus.OK);
	}
}
