package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Employee email = employeeRepository.findByEmail(username);
		
		if(email==null)
		{
		   throw new UsernameNotFoundException("User Doesn't exist");	
		}
		else
		{
		return new CustomUser(email);
		}
	}

}
