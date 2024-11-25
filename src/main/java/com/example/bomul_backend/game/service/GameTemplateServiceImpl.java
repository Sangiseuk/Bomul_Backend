package com.example.bomul_backend.game.service;

import com.example.bomul_backend.game.model.Dao.GameTemplateDao;
import com.example.bomul_backend.game.model.Entity.GameTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameTemplateServiceImpl implements GameTemplateService {

    private final GameTemplateDao gameTemplateDao;

    @Autowired
    public GameTemplateServiceImpl(GameTemplateDao gameTemplateDao) {
        this.gameTemplateDao = gameTemplateDao;
    }

    @Override
    public void createGameTemplate(GameTemplate gameTemplate) {
        gameTemplate.setCreatedAt(LocalDateTime.now());
        gameTemplate.setUpdatedAt(LocalDateTime.now());
        gameTemplateDao.insertGameTemplate(gameTemplate);
    }

    @Override
    public GameTemplate getGameTemplateById(int mapId) {
        return gameTemplateDao.selectGameTemplateById(mapId);
    }

    @Override
    public List<GameTemplate> getAllGameTemplates() {
        return gameTemplateDao.selectAllGameTemplates();
    }

    @Override
    public void updateGameTemplate(GameTemplate gameTemplate) {
        gameTemplateDao.updateGameTemplate(gameTemplate);
    }

    @Override
    public void deleteGameTemplate(int mapId) {
        gameTemplateDao.deleteGameTemplate(mapId);
    }
}
