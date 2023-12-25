package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.EmployeeLogin;
import com.example.demo.response.ResponseHandler;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public ResponseEntity<Object> authenticateUser(@RequestBody EmployeeLogin employeeLogin)
	{
		org.springframework.security.core.Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employeeLogin.getUsername(), employeeLogin.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return ResponseHandler.responsebuilder("Success", HttpStatus.OK, employeeLogin);
	}
	
}
