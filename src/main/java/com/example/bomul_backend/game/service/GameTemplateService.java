package com.example.bomul_backend.game.service;

import com.example.bomul_backend.game.model.dto.GameTemplateRequest;
import com.example.bomul_backend.game.model.dto.GameTemplateRequest;

public interface GameTemplateService {
    int createGameTemplate(GameTemplateRequest request);
    int updateGameTemplate(int templateId, GameTemplateRequest request);
}
