package com.example.bomul_backend.game.model.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GameResult {
	private int gameId;
	private int mapId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
}
