package com.example.bomul_backend.game.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class GameTemplate {
    private int mapId;
    private int operatorId;
    private int scopeId;
    private int maxParticipants;
    private String announcementText;
    private int feedbackRange;
    private int maxFeedbackLevel;
    private List<MarkerTemplate> markers;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
