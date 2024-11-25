package com.example.bomul_backend.game.service;

import com.example.bomul_backend.common.Position;
import com.example.bomul_backend.game.model.dao.GameTemplateDao;
import com.example.bomul_backend.game.model.dto.GameTemplateRequest;
import com.example.bomul_backend.game.model.dto.MarkerRequest;
import com.example.bomul_backend.game.model.entity.GameTemplate;
import com.example.bomul_backend.game.model.entity.MarkerTemplate;
import com.example.bomul_backend.game.model.entity.Scope;
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
        Scope scope = new Scope();
        scope.setScopeType(Scope.ScopeType.values()[request.getScopeType()]);
        scope.setCreatedAt(LocalDateTime.now());
        gameTemplateDao.insertScope(scope);

        // GameTemplete 생성
        GameTemplate gameTemplate = new GameTemplate();
        gameTemplate.setOperatorId(request.getHostId());
        gameTemplate.setScopeId(request.getScopeId());
        gameTemplate.setMaxParticipants(request.getMaxParticipants());
        gameTemplate.setAnnouncementText(request.getAnnouncementText());
        gameTemplate.setFeedbackRange(request.getFeedbackRange());
        gameTemplate.setMaxFeedbackLevel(request.getMaxFeedbackLevel());
        gameTemplate.setCreatedAt(LocalDateTime.now());

        // GameTemplete 저장
        int result =  gameTemplateDao.insertGameTemplate(gameTemplate);
        int mapId = gameTemplate.getMapId();
        // 마커 리스트 저장
        if (request.getMarkers() != null) {
            for (MarkerRequest markerRequest : request.getMarkers()) {
                MarkerTemplate marker = new MarkerTemplate();
                marker.setMapId(mapId);
                marker.setType(MarkerTemplate.MarkerType.values()[markerRequest.getType()]);
                marker.setPosition(new Position(markerRequest.getLatitude(), markerRequest.getLongitude()));
                marker.setVisible(markerRequest.isVisible());
                marker.setViewCount(markerRequest.getViewCount());
                marker.setContentText(markerRequest.getContentText());
                marker.setContentImgUrl(markerRequest.getContentImgUrl());
                marker.setCreatedAt(LocalDateTime.now());
                marker.setUpdatedAt(LocalDateTime.now());

                gameTemplateDao.insertMarker(marker);
            }
        }

        return result;
    }

    @Override
    public int updateGameTemplate(int templateId, GameTemplateRequest request) {
        GameTemplate gameTemplate = getGameTemplate(templateId);
        // 업데이트 로직
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
