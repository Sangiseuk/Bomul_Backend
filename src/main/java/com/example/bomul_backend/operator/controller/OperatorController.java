package com.example.bomul_backend.operator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bomul_backend.operator.service.OperatorService;

@RestController("/operator")
public class OperatorController {
	OperatorService operatorService;
	
	@Autowired
	public OperatorController(OperatorService operatorService) {
		this.operatorService = operatorService;
	}
}
