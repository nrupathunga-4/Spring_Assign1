package com.example.demo.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseHandler {

	public static ResponseEntity<Object> responsebuilder(String status,HttpStatus statuscode,Object objectresponse)
	{
		Map<String, Object> map=new HashMap<>();
		map.put("status", status);
		map.put("statuscode", statuscode.value());
		map.put("data", objectresponse);
		return new ResponseEntity<>(map,statuscode);
	}

}
