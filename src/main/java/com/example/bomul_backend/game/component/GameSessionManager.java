package com.example.bomul_backend.game.component;

import com.example.bomul_backend.common.exceptions.FailGameCreationException;
import com.example.bomul_backend.game.model.pojo.GameInfo;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Component
public class GameSessionManager {
    private final Map<String, GameInfo> gameSessionData = new ConcurrentHashMap<>();

    public boolean verifyGameCode(String gameCode) {
        if(gameCode.length() != 6) {
            return false;
        }
        return gameSessionData.containsKey(gameCode);
    }

    public String addGame(GameInfo gameInfo) throws FailGameCreationException {
        String genUUID = UUID.randomUUID().toString().substring(0, 6);
        int genCount = 0;
        while (!gameSessionData.containsKey(genUUID) || genCount < 20) {
            genUUID = UUID.randomUUID().toString().substring(0, 6);
            genCount++;
        }
        if(gameSessionData.containsKey(genUUID)){
            throw new FailGameCreationException("UUID Conflict");
        }
        else {
            gameSessionData.put(genUUID, gameInfo);
            return genUUID;
        }
    }

    public GameInfo getGame(String gameCode) {
        return gameSessionData.get(gameCode);
    }

    public void removeGame(String gameCode) {
        gameSessionData.remove(gameCode);
    }
}
