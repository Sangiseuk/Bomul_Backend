package com.example.bomul_backend.game.model.dao;

import com.example.bomul_backend.game.model.entity.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameTemplateDao {

    int insertGameTemplate(GameTemplate gameTemplate);
    GameTemplate selectGameTemplateById(int mapId);
    int updateGameTemplate(GameTemplate gameTemplate);
    int insertScope(Scope scope);
    int insertMarker(MarkerTemplate marker);
}
