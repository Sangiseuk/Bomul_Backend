package com.example.bomul_backend.game.service;

import com.example.bomul_backend.game.model.Entity.GameTemplate;

import java.util.List;

public interface GameTemplateService {
    void createGameTemplate(GameTemplate gameTemplate);
    GameTemplate getGameTemplate(int templateId);
    List<GameTemplate> getAllGameTemplates();
    GameTemplate updateGameTemplate(int templateId, GameTemplate gameTemplate);
    void deleteGameTemplate(int templateId);
}
