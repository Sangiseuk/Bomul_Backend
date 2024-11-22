package com.example.bomul_backend.game.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameParticipantController {
    @MessageMapping("/{gameCode}/join")
    @SendTo("/broadCast/{gameCode}/join")
    public String joinGame(@DestinationVariable String gameCode, String message) {
        return "";
    }
}
