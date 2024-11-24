package com.example.bomul_backend.game.controller;

import com.example.bomul_backend.common.Position;
import com.example.bomul_backend.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GameParticipantController {
    GameService gameService;

    @Autowired
    public GameParticipantController(GameService gameService) {
        this.gameService = gameService;
    }

    @MessageMapping("{gameCode}/send-location") // 클라이언트에서 보내는 메시지의 경로
    @SendTo("/operator/{gameCode}/receive-location") // 브로커를 통해 전달될 경로
    public Position broadcastLocation(SimpMessageHeaderAccessor headerAccessor, @PathVariable String gameCode, Position locationData) {
        return gameService.sendLocation(headerAccessor.getSessionId(), gameCode, locationData); // 클라이언트로 반환
    }


}
