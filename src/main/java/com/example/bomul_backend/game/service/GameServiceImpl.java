package com.example.bomul_backend.game.service;

import com.example.bomul_backend.game.model.dto.CreateGameDto;
import com.example.bomul_backend.game.model.dto.CreateGameResponseDto;
import com.example.bomul_backend.game.model.pojo.GameInfo;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {
    private final HttpSession httpSession;
    Map<String, GameInfo> gameSessionData;

    public GameServiceImpl(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public ResponseEntity<CreateGameResponseDto> createGame(CreateGameDto createGameDto) {
        try {
            String genUUID = UUID.randomUUID().toString().substring(0, 6);
            int genCount = 0;
            while (!gameSessionData.containsKey(genUUID) || genCount < 20) {
                genUUID = UUID.randomUUID().toString().substring(0, 6);
                genCount++;
            }
            if(gameSessionData.containsKey(genUUID))
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "UUID Conflict.");
            else {
                GameInfo gameInfo = GameInfo.builder()
                        .hostSessionId(httpSession.getId())
                        .scope(createGameDto.scope())
                        .maxParticipants(createGameDto.maxParticipants())
                        .announcementText(createGameDto.announcementText())
                        .feedbackRange(createGameDto.feedbackRange())
                        .maxFeedbackLevel(createGameDto.maxFeedbackLevel())
                        .markerList(createGameDto.markerList())
                        .build();
                gameSessionData.put(genUUID, gameInfo);
                return new ResponseEntity<>(new CreateGameResponseDto(genUUID), HttpStatus.CREATED);
            }
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
