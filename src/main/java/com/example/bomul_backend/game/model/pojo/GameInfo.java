package com.example.bomul_backend.game.model.pojo;

import com.example.bomul_backend.game.model.entity.Scope;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class GameInfo {
    private final String hostSessionId;
    private final Scope scope;
    private final int maxParticipants;
    private final String announcementText;
    private final int feedbackRange;
    private final int maxFeedbackLevel;
    private List<Marker> markerList;
}
