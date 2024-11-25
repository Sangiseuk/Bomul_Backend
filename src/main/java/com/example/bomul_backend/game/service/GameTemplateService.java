package com.example.bomul_backend.game.service;

import com.example.bomul_backend.game.model.Dto.GameTemplateRequest;

public interface GameTemplateService {
    int createGameTemplate(GameTemplateRequest request);
    int updateGameTemplate(int templateId, GameTemplateRequest request);

}
