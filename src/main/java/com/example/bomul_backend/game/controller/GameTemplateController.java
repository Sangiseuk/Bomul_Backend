package com.example.bomul_backend.game.controller;

import com.example.bomul_backend.game.model.dto.GameTemplateRequest;
import com.example.bomul_backend.game.service.GameTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game-template")
public class GameTemplateController {

	private final GameTemplateService gameTemplateService;

	@Autowired
	public GameTemplateController(GameTemplateService gameTemplateService) {
		this.gameTemplateService = gameTemplateService;
	}

	// 게임 템플릿 생성
	@PostMapping("/create")
	public ResponseEntity<String> createGameTemplate(@RequestBody GameTemplateRequest gameTemplateRequest) {
		try {
			gameTemplateService.createGameTemplate(gameTemplateRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body("GameTemplate created successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create GameTemplate.");
		}
	}

	// 게임 템플릿 수정
	@PostMapping("/change-template/{templateId}")
	public ResponseEntity<String> changeGameTemplate(@PathVariable int templateId, @RequestBody GameTemplateRequest gameTemplateRequest) {
		try {
			gameTemplateService.updateGameTemplate(templateId, gameTemplateRequest);
			return ResponseEntity.ok("GameTemplate updated successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update GameTemplate.");
		}
	}

}
