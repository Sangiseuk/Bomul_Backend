package com.example.bomul_backend.game.model.dto;

import com.example.bomul_backend.game.model.entity.CircleScope;
import com.example.bomul_backend.game.model.entity.CustomScope;
import com.example.bomul_backend.game.model.entity.RectangleScope;
import com.example.bomul_backend.game.model.entity.Scope;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameTemplateRequest {
    private int hostId;
    private Scope scope;
    private int maxParticipants;
    private String announcementText;
    private int feedbackRange; // 200m, 150m, 등
    private int maxFeedbackLevel; // 진동 횟수
    private List<MarkerRequest> markers; // 마커 데이터
}
