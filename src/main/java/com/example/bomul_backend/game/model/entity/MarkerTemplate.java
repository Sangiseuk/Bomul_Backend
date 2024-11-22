package com.example.bomul_backend.game.model.entity;

import com.example.bomul_backend.common.Position;
import com.example.bomul_backend.game.model.entity.Marker.MarkerType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MarkerTemplate {

	private int markerId;
	private int mapId;
	private MarkerType type;
	private Position position;
	private boolean isVisible;
	private int viewCount;
	private String contentText;
	private String contentImgUrl;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
