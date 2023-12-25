package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EmployeeAlreadyExist {

public static ResponseEntity<Object> alreadyExist(String msg,HttpStatus statusCode,String status)
{
	Map<String, Object> map=new HashMap<>();
	map.put("Message", msg);
	map.put("statusCode", statusCode.value());
	map.put("status", status);
	return new ResponseEntity<>(map,statusCode);
}
	
}
