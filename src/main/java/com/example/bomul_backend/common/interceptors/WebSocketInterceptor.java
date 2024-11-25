package com.example.bomul_backend.common.interceptors;

import com.example.bomul_backend.common.utils.JwtUtil;
import com.example.bomul_backend.game.component.GameSessionManager;
import com.example.bomul_backend.game.model.pojo.GameInfo;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@Component
public class WebSocketInterceptor implements HandshakeInterceptor {
    private final GameSessionManager gameSessionManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public WebSocketInterceptor(GameSessionManager gameSessionManager, JwtUtil jwtUtil) {
        this.gameSessionManager = gameSessionManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * queryString에서 gameCode 파싱해서 반환하는 함수
     * @param queryString
     * @return gameCode (유효하지 않은 gameCode인 경우 null 반환)
     */
    private String getGameCodeFromQueryString(String queryString) {
        final String queryKey = "gameCode=";
        if (queryString == null || !queryString.startsWith(queryKey)) {
            return null;
        }

        // gameCode 검증
        String gameCode = queryString.substring(queryKey.length());
        if(!gameSessionManager.verifyGameCode(gameCode)) {
            return null;
        }
        return gameCode;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // queryParam에 기록된 gameCode 가져오기
        String gameCode = getGameCodeFromQueryString(request.getURI().getQuery());
        if(gameCode == null){
            return false;
        }

        GameInfo.GameStatus gameStatus = gameSessionManager.getGame(gameCode).getGameStatus();
        //JWT 토큰 검사
        Optional<String> jwtTokens = jwtUtil.getJwtTokenFromCookies(request);
        if((jwtTokens.isEmpty() || !jwtUtil.isTokenValid(jwtTokens.get(), gameCode))
            && gameStatus != GameInfo.GameStatus.CREATED) {
          return false;
        } else if (gameStatus == GameInfo.GameStatus.CREATED) {
            ResponseCookie cookie = ResponseCookie.from("AuthToken", jwtUtil.generateToken(gameCode))
                    .httpOnly(true)
                    .sameSite("Strict")
                    .path(request.getURI().getPath())
                    .maxAge(3600 * 24 * 2)
                    .build();
            response.getHeaders().add("Set-Cookie", cookie.toString());
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
