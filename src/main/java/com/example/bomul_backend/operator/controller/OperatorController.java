package com.example.bomul_backend.operator.controller;

import com.example.bomul_backend.operator.model.Entity.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.bomul_backend.operator.service.OperatorService;

@RestController
@RequestMapping("/operator")
public class OperatorController {
	OperatorService operatorService;
	
	@Autowired
	public OperatorController(OperatorService operatorService) {
		this.operatorService = operatorService;
	}

	@PostMapping("sign-up")
	public ResponseEntity<String> signup(@RequestBody Operator operator) {
		try {
			operatorService.signup(operator);
			return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Failed to register user: " + e.getMessage());
		}
	}
}