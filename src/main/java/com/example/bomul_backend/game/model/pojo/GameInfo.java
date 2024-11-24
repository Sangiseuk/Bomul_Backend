package com.example.bomul_backend.game.model.pojo;

import com.example.bomul_backend.common.Position;
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

    /**
     * 현재 locationData와 가장 인접한 Marker의 햅틱 피드백 레벨을 반환
     *
     * @param locationData 계산 기준이 되는 location
     * @return 햅틱 피드백 레벨
     */
    public int getFeedBackLevel(Position locationData) {
        int feedBackLevel = -1;
        double minDistance = Double.MAX_VALUE;
        for (Marker marker : markerList) {
            double distance = marker.getPosition().getDistance(locationData);
            if (minDistance > distance) {
                minDistance = distance;
                int currLevel = (int) (distance / feedbackRange);
                feedBackLevel = currLevel < maxFeedbackLevel ? currLevel : feedBackLevel;
            }
        }
        return feedBackLevel;
    }
}
