package com.example.bomul_backend.game.controller;

import com.example.bomul_backend.game.model.Entity.GameTemplate;
import com.example.bomul_backend.game.service.GameTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public ResponseEntity<String> createGameTemplate(@RequestBody GameTemplate gameTemplate) {
		try {
			gameTemplateService.createGameTemplate(gameTemplate);
			return ResponseEntity.status(HttpStatus.CREATED).body("GameTemplate created successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create GameTemplate.");
		}
	}

	// 게임 템플릿 조회
	@GetMapping("/{templateId}")
	public ResponseEntity<GameTemplate> getGameTemplate(@PathVariable int templateId) {
		GameTemplate gameTemplate = gameTemplateService.getGameTemplate(templateId);
		return ResponseEntity.ok(gameTemplate);
	}

	// 모든 게임 템플릿 조회 (필요시 페이징 추가 가능)
	@GetMapping("/all")
	public ResponseEntity<List<GameTemplate>> getAllGameTemplates() {
		List<GameTemplate> templates = gameTemplateService.getAllGameTemplates();
		return ResponseEntity.ok(templates);
	}

	// 게임 템플릿 업데이트
	@PutMapping("/update/{templateId}")
	public ResponseEntity<GameTemplate> updateGameTemplate(
			@PathVariable int templateId,
			@RequestBody GameTemplate gameTemplate) {
		GameTemplate updatedTemplate = gameTemplateService.updateGameTemplate(templateId, gameTemplate);
		return ResponseEntity.ok(updatedTemplate);
	}

	// 게임 템플릿 삭제
	@DeleteMapping("/delete/{templateId}")
	public ResponseEntity<Void> deleteGameTemplate(@PathVariable int templateId) {
		gameTemplateService.deleteGameTemplate(templateId);
		return ResponseEntity.noContent().build();
	}
}
