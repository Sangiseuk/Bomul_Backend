package com.example.bomul_backend.game.controller;

import com.example.bomul_backend.game.model.dto.CreateGameDto;
import com.example.bomul_backend.game.model.dto.CreateGameResponseDto;
import com.example.bomul_backend.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GameOperatorController {
    GameService gameService;

    @Autowired
    public GameOperatorController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create-game")
    public ResponseEntity<CreateGameResponseDto> createGame(@RequestBody CreateGameDto createGameDto) {
        return gameService.createGame(createGameDto);
    }
}
