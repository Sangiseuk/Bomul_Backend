package com.example.bomul_backend.game.model.dao;

import com.example.bomul_backend.game.model.entity.GameTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameTemplateDao {

    // 게임 템플릿 생성
    void insertGameTemplate(GameTemplate gameTemplate);

    // ID로 게임 템플릿 조회
    GameTemplate selectGameTemplateById(int mapId);

    // 모든 게임 템플릿 조회
    List<GameTemplate> selectAllGameTemplates();

    // 게임 템플릿 업데이트
    void updateGameTemplate(GameTemplate gameTemplate);

    // 게임 템플릿 삭제
    void deleteGameTemplate(int mapId);
}
