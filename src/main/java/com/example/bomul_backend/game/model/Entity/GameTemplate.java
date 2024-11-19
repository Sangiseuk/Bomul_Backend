package com.example.bomul_backend.game.model.Entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GameTemplate {
    private int mapId;
    private int hostId;
    private int scopeId;
    private int maxParticipants;
    private String announcementText;
    private int feedbackRange;
    private int maxFeedbackLevel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
