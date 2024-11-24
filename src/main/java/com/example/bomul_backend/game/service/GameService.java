package com.example.bomul_backend.game.service;

import com.example.bomul_backend.common.Position;
import com.example.bomul_backend.game.model.dto.CreateGameDto;
import com.example.bomul_backend.game.model.dto.CreateGameResponseDto;
import org.springframework.http.ResponseEntity;

public interface GameService {
    ResponseEntity<CreateGameResponseDto> createGame(CreateGameDto createGameDto);

    Position sendLocation(String sessionId, String gameCode, Position locationData);
}
