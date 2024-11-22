package com.example.bomul_backend.game.model.entity;

import java.time.LocalDateTime;

import com.example.bomul_backend.common.Position;
import lombok.Getter;

@Getter
public class Marker {
	public enum MarkerType {
		TARGET, EVENT;

		public MarkerType getByIndex(int index) {
			try {
				return MarkerType.values()[index];
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IllegalArgumentException("Invalid MarkerType Index : " + index);
			}
		}
	}

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
