package com.example.bomul_backend.game.service;

import com.example.bomul_backend.common.Position;
import com.example.bomul_backend.game.model.dto.CreateGameDto;
import com.example.bomul_backend.game.model.dto.CreateGameResponseDto;
import com.example.bomul_backend.game.model.pojo.GameInfo;
import com.example.bomul_backend.game.model.pojo.Marker;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {
    private final HttpSession httpSession;
    private final SimpMessagingTemplate messagingTemplate;
    Map<String, GameInfo> gameSessionData;

    @Autowired
    public GameServiceImpl(HttpSession httpSession, SimpMessagingTemplate messagingTemplate) {
        this.httpSession = httpSession;
        this.messagingTemplate = messagingTemplate;
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

    private void sendFeedBackLevel(String sessionId, String gameCode, Position locationData) {
        GameInfo gameInfo = gameSessionData.get(gameCode); // 추후 기능 분리를 고려해 해당 Line 사용
        if(gameInfo == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        int feedBackLevel = gameInfo.getFeedBackLevel(locationData);
        if(feedBackLevel == -1)
            return;

        StringBuilder destStringBuilder = new StringBuilder();
        destStringBuilder.append("/participant/").append(gameCode).append("/feedback");
        String destination = destStringBuilder.toString();
        messagingTemplate.convertAndSendToUser(sessionId, destination, feedBackLevel);
    }

    @Override
    public Position sendLocation(String sessionId, String gameCode, Position locationData) {
        sendFeedBackLevel(sessionId, gameCode, locationData);
        return locationData;
    }

    @Override
    public void findMarker(String sessionId, String gameCode, Position locationData) {
        GameInfo gameInfo = gameSessionData.get(gameCode); // 추후 기능 분리를 고려해 해당 Line 사용
        if(gameInfo == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        String broadCastDestination = new StringBuilder().append("/broadCast/").append(gameCode).append("/marker-update").toString();
        String returnDestination = new StringBuilder().append("/participant/").append(gameCode).append("/find-marker").toString();

        Marker nearMarker = gameInfo.findMarker(locationData);
        if(nearMarker != null) {
            messagingTemplate.convertAndSendToUser(sessionId, returnDestination, nearMarker);
            messagingTemplate.convertAndSend(broadCastDestination, gameInfo.getMarkerList());
        }
        else {
            messagingTemplate.convertAndSendToUser(sessionId, returnDestination, Optional.empty());
        }
    }
}
