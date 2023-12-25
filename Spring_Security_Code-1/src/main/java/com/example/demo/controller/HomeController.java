package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeAlreadyExist;
import com.example.demo.exception.EmployeeNotFound;
import com.example.demo.repository.EmployeeRegister;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.response.ResponseHandler;
import com.example.demo.response.ResponseHandlerDelete;
import com.example.demo.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class HomeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRegister employeeRegister;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	

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
		Optional<Employee> optional=employeeRegister.findByEmail(employee.getEmail());
		if(optional.isPresent())
		{
			return EmployeeAlreadyExist.alreadyExist("Employee Altready Exist in Database", HttpStatus.FOUND, "Failed");
		}
		return ResponseHandler.responsebuilder("sucess",HttpStatus.OK, employeeService.saveEmployee(employee));
	}
	
	@GetMapping("/admin/getall")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Employee> getAll()
	{
		return employeeService.getAll();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable  int id) throws EmployeeNotFound
	{
		return ResponseHandler.responsebuilder("Sucess", HttpStatus.OK,  employeeService.getEmployeeById(id));   
	}
	
	@DeleteMapping("/admin/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable  int id) throws EmployeeNotFound
	{
		Optional<Employee> employee=employeeRepository.findById(id);
		if(employee.isEmpty())
		{
		   return ResponseHandlerDelete.response("Failed", HttpStatus.NOT_FOUND, "Employee Doesn't Exist In Database");	
		}
		employeeService.deleteEmployee(id);
		return ResponseHandlerDelete.response("Sucess", HttpStatus.OK, "Employee is Deleted From Database");
	}
	
	@PutMapping("/user/{email}")
	public ResponseEntity<Object> updatePassword(@RequestBody Employee employee,@PathVariable String email) throws EmployeeNotFound
	{
		Employee employee2=employeeRepository.findByEmail(email);
		if(employee2==null)
		{
			return ResponseHandlerDelete.response("Failed", HttpStatus.NOT_FOUND,"Employee Doesn't Exist In DataBase");
		}
		return ResponseHandler.responsebuilder("sucessfully Changed", HttpStatus.OK, employeeService.updatePassword(employee, email));
	}
	
}
