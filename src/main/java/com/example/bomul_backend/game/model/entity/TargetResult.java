package com.example.bomul_backend.game.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TargetResult {
	private int resultId;
	private int gameId;
	private String playerName;
	private String contentText;
	private String contentImageUrl;
	private LocalDateTime foundAt;
}
