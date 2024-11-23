package com.example.bomul_backend.game.controller;

import com.example.bomul_backend.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameParticipantController {
    GameService gameService;

    @Autowired
    public GameParticipantController(GameService gameService) {
        this.gameService = gameService;
    }

    @MessageMapping("/{gameCode}/join")
    @SendTo("/broadCast/{gameCode}/join")
    public String joinGame(@DestinationVariable String gameCode, String message) {
        return "";
    }
}
