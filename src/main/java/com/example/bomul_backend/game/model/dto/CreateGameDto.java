package com.example.bomul_backend.game.model.dto;

import com.example.bomul_backend.game.model.entity.Scope;
import com.example.bomul_backend.game.model.pojo.Marker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public record CreateGameDto(Scope scope, int maxParticipants, String announcementText, int feedbackRange,
                            int maxFeedbackLevel, List<Marker> markerList) {
}
