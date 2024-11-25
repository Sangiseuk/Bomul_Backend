package com.example.bomul_backend.game.model.dto;

import com.example.bomul_backend.game.model.entity.CircleScope;
import com.example.bomul_backend.game.model.entity.CustomScope;
import com.example.bomul_backend.game.model.entity.RectangleScope;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameTemplateRequest {
    private int hostId;
    private int scopeType; // 0: CIRCLE, 1: RECTANGLE, 2: CUSTOM
    private CircleScope circleScope; // 원형 스코프
    private RectangleScope rectangleScope; // 사각형 스코프
    private CustomScope customScope;
    private int maxParticipants;
    private String announcementText;
    private int feedbackRange; // 200m, 150m, 등
    private int maxFeedbackLevel; // 진동 횟수
    private List<MarkerRequest> markers; // 마커 데이터
}
