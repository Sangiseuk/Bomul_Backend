package com.example.bomul_backend.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.bomul_backend.game.service.GameTemplateService;

@RestController
public class GameTemplateController {
	GameTemplateService gameTemplateService;
	
	@Autowired
	public GameTemplateController(GameTemplateService gameTemplateService) {
		this.gameTemplateService = gameTemplateService;
	}
}
