package com.example.bomul_backend.operator.controller;

import com.example.bomul_backend.operator.model.Entity.Operator;
import jakarta.servlet.http.HttpSession;
import org.springdoc.core.service.RequestBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.bomul_backend.operator.service.OperatorService;

@RestController
@RequestMapping("/operator")
public class OperatorController {
	private final RequestBodyService requestBodyBuilder;
	OperatorService operatorService;
	
	@Autowired
	public OperatorController(OperatorService operatorService, RequestBodyService requestBodyBuilder) {
		this.operatorService = operatorService;
		this.requestBodyBuilder = requestBodyBuilder;
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

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Operator operator, HttpSession session){
		String rawPassword = operator.getPassword().getPassword();

		Operator tmp = operatorService.login(operator.getEmail(), rawPassword);
		if(tmp!=null){
			session.setAttribute("operator", tmp.getNickname());
			return ResponseEntity.ok("Login successful");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
		}
	}

	@GetMapping
	public ResponseEntity<String> logout(HttpSession session){
		session.invalidate();
		return ResponseEntity.ok("Logout successful");
	}
}