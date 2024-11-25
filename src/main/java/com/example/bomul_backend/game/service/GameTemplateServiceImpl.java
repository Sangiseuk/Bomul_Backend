package com.example.bomul_backend.game.service;

import com.example.bomul_backend.common.Position;
import com.example.bomul_backend.game.model.dao.GameTemplateDao;
import com.example.bomul_backend.game.model.dto.GameTemplateRequest;
import com.example.bomul_backend.game.model.dto.MarkerRequest;
import com.example.bomul_backend.game.model.entity.*;
import com.example.bomul_backend.game.model.pojo.Marker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class GameTemplateServiceImpl implements GameTemplateService {

    private final GameTemplateDao gameTemplateDao;

    public GameTemplateServiceImpl(GameTemplateDao gameTemplateDao) {
        this.gameTemplateDao = gameTemplateDao;
    }

    @Transactional
    @Override
    public int createGameTemplate(GameTemplateRequest request) {
        // 1. Scope
        Scope scope = Scope.builder()
                .scopeType(Scope.ScopeType.values()[request.getScopeType()])
                .createdAt(LocalDateTime.now())
                .build();
        gameTemplateDao.insertScope(scope);

        int scopeId = scope.getScopeId();

        // Scope Type 별 저장
        switch(scope.getScopeType()){
            case CIRCLE -> {
                CircleScope circleScope = CircleScope.builder()
                        .scopeId(scopeId)
                        .centerPosition(new Position(
                                request.getCircleScope().getCenterPosition().getLatitude(),
                                request.getCircleScope().getCenterPosition().getLongitude()))
                        .radius(request.getCircleScope().getRadius())
                        .build();

            }
            case RECTANGLE -> {
                RectangleScope rectangleScope = RectangleScope.builder()
                        .scopeId(scopeId)
                        .topLeftPosition(new Position(
                                request.getRectangleScope().getTopLeftPosition().getLatitude(),
                                request.getRectangleScope().getTopLeftPosition().getLongitude()))
                        .bottomRightPosition(new Position(
                                request.getRectangleScope().getBottomRightPosition().getLatitude(),
                                request.getRectangleScope().getBottomRightPosition().getLongitude()))
                        .build();
                gameTemplateDao.insertRectangleScope(rectangleScope);
            }
            case CUSTOM -> {
                CustomScope customScope = CustomScope.builder()
                        .scopeId(scopeId)
                        .customPoint(request.getCustomScope().getCustomPoint())
                        .build();
                gameTemplateDao.insertCustomScope(customScope);
            }
        }

        // 2. GameTemplete
        GameTemplate gameTemplate = GameTemplate.builder()
                .operatorId(request.getHostId())
                .scopeId(scopeId)
                .maxParticipants(request.getMaxParticipants())
                .announcementText(request.getAnnouncementText())
                .feedbackRange(request.getFeedbackRange())
                .maxFeedbackLevel(request.getMaxFeedbackLevel())
                .createdAt(LocalDateTime.now())
                .build();
        int result = gameTemplateDao.insertGameTemplate(gameTemplate);

        int mapId = gameTemplate.getMapId();

        // 3. Marker
        if (request.getMarkers() != null) {
            for (MarkerRequest markerRequest : request.getMarkers()) {
                MarkerTemplate marker = MarkerTemplate.builder()
                        .mapId(mapId)
                        .type(Marker.MarkerType.values()[markerRequest.getType()])
                        .position(new Position(
                                markerRequest.getPosition().getLatitude(),
                                markerRequest.getPosition().getLongitude()))
                        .isVisible(markerRequest.isVisible())
                        .viewCount(markerRequest.getViewCount())
                        .contentText(markerRequest.getContentText())
                        .contentImgUrl(markerRequest.getContentImgUrl())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();

                gameTemplateDao.insertMarker(marker);
            }
        }
        return result;
    }

    @Override
    public int updateGameTemplate(int templateId, GameTemplateRequest request) {
        GameTemplate gameTemplate = getGameTemplate(templateId);
        gameTemplate.setMaxParticipants(request.getMaxParticipants());
        gameTemplate.setAnnouncementText(request.getAnnouncementText());
        gameTemplate.setFeedbackRange(request.getFeedbackRange());
        gameTemplate.setMaxFeedbackLevel(request.getMaxFeedbackLevel());
        gameTemplate.setUpdatedAt(LocalDateTime.now());
        return gameTemplateDao.updateGameTemplate(gameTemplate);
    }

    private GameTemplate getGameTemplate(int templateId) {
        return gameTemplateDao.selectGameTemplateById(templateId);
    }


}
