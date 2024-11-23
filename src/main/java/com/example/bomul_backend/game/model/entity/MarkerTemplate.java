package com.example.bomul_backend.game.model.entity;

import com.example.bomul_backend.common.Position;
import com.example.bomul_backend.game.model.pojo.Marker.MarkerType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class MarkerTemplate {
	@Setter
	private int markerId;

	@Setter
	private int mapId;

	@Setter
	private MarkerType type;

	@Setter
	private Position position;

	@Setter
	private boolean isVisible;

	private int viewCount;
	private String contentText;
	private String contentImgUrl;

	@Setter
	private LocalDateTime createdAt;

	@Setter
	private LocalDateTime updatedAt;

	public void setViewCount(int viewCount) throws IllegalArgumentException {
		if(viewCount <= 0) {
			viewCount = 0;
			throw new IllegalArgumentException("Invalid viewCount : " + viewCount);
		}
		this.viewCount = viewCount;
	}

	public void setContentText(String contentText) throws IllegalArgumentException, NullPointerException {
		if(contentText == null) {
			throw new NullPointerException("contentText is null");
		}
		if(contentText.length() > 1000) {
			throw new IllegalArgumentException("Marker contentText is too long : " + contentText.length());
		}
		this.contentText = contentText;
	}

	public void setContentImgUrl(String contentImgUrl) throws IllegalArgumentException, NullPointerException {
		if(contentImgUrl == null) {
			throw new NullPointerException("contentImgUrl is null");
		}
		if(contentImgUrl.length() > 2000) {
			throw new IllegalArgumentException("Marker contentImgUrl is too long : " + contentImgUrl.length());
		}
		this.contentImgUrl = contentImgUrl;
	}
}
