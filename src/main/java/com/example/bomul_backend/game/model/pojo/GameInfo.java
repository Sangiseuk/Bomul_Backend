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
    public enum GameStatus {
        CREATED, IN_PROGRESS, FINISHED
    }

    private final String hostSessionId;
    private final Scope scope;
    private final int maxParticipants;
    private final String announcementText;
    private final int feedbackRange;
    private final int maxFeedbackLevel;
    @Builder.Default
    private GameStatus gameStatus = GameStatus.CREATED;
    private List<Marker> markerList;


    private Marker getNearestMarker(Position locationData) {
        Marker nearMarker = null;
        double minDistance = Double.MAX_VALUE;
        for (Marker marker : markerList) {
            double distance = marker.getPosition().getDistance(locationData);
            if (minDistance > distance) {
                minDistance = distance;
                nearMarker = marker;
            }
        }
        return nearMarker;
    }
    /**
     * 현재 locationData와 가장 인접한 Marker의 햅틱 피드백 레벨을 반환
     *
     * @param locationData 계산 기준이 되는 location
     * @return 햅틱 피드백 레벨 (인접한 마커가 없을 시, -1)
     */
    public int getFeedBackLevel(Position locationData) {
        int feedBackLevel = -1;
        Marker nearMarker = getNearestMarker(locationData);

        assert nearMarker != null; // 절대 null이면 안됨

        double distance = nearMarker.getPosition().getDistance(locationData);
        int nearMarkerLevel = (int) (distance / feedbackRange);
        feedBackLevel = nearMarkerLevel < maxFeedbackLevel ? nearMarkerLevel : -1;

        return feedBackLevel;
    }

    public Marker findMarker(Position locationData) {
        Marker nearMarker = getNearestMarker(locationData);

        assert nearMarker != null; //절대 null이면 안됨

        double distance = nearMarker.getPosition().getDistance(locationData);
        int nearMarkerLevel = (int) (distance / feedbackRange);
        int feedBackLevel = nearMarkerLevel < maxFeedbackLevel ? nearMarkerLevel : -1;
        if(feedBackLevel == 0) {
            nearMarker.setViewCount(nearMarker.getViewCount() - 1);
            return nearMarker;
        }
        else {
            return null;
        }
    }
}
