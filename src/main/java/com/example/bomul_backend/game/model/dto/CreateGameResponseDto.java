package com.example.bomul_backend.game.model.dto;

public record CreateGameResponseDto(String gameCode) {
    public CreateGameResponseDto {
        if (gameCode.length() > 6)
            throw new IllegalArgumentException("Game code must contain at most 6 characters");
    }
}
