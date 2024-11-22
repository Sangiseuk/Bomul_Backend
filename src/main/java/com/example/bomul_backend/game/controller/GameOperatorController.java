package com.example.bomul_backend.game.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameOperatorController {
    @MessageMapping("/{gameCode}/create")
    @SendTo("/operator/{gameCode}/create")
    public String createGame(@DestinationVariable String gameCode, String message) {
        return "";
    }
}
