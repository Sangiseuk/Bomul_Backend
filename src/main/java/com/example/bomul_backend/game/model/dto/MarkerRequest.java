package com.example.bomul_backend.game.model.dto;

import com.example.bomul_backend.common.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarkerRequest {
    private int type; // 0: TARGET, 1: EVENT
    private Position position;
    private boolean isVisible;
    private int viewCount;
    private String contentText;
    private String contentImgUrl;
}
