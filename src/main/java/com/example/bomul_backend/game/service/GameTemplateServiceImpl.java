package com.example.bomul_backend.game.service;

import com.example.bomul_backend.common.Position;
import com.example.bomul_backend.game.model.dao.GameTemplateDao;
import com.example.bomul_backend.game.model.dto.GameTemplateRequest;
import com.example.bomul_backend.game.model.dto.MarkerRequest;
import com.example.bomul_backend.game.model.entity.*;
import com.example.bomul_backend.game.model.pojo.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GameTemplateServiceImpl implements GameTemplateService {

    private final GameTemplateDao gameTemplateDao;
    Logger logger = Logger.getLogger(GameTemplateServiceImpl.class.getName());

    @Autowired
    public GameTemplateServiceImpl(GameTemplateDao gameTemplateDao) {
        this.gameTemplateDao = gameTemplateDao;
    }

    @Transactional
    @Override
    public int createGameTemplate(GameTemplateRequest request) {
        // 1. Scope
        logger.log(Level.INFO, request.getScope().getScopeType().toString());
        gameTemplateDao.insertScope(request.getScope());

        int scopeId = request.getScope().getScopeId();
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
