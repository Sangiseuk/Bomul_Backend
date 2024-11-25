package com.example.bomul_backend.game.model.entity;

import com.example.bomul_backend.common.Position;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class RectangleScope extends Scope {
    private Position topLeftPosition;
    private Position bottomRightPosition;

    public RectangleScope(int scopeId,
                          ScopeType scopeType,
                          LocalDateTime createdAt,
                          LocalDateTime updatedAt,
                          Position topLeftPosition,
                          Position bottomRightPosition) {
        super(scopeId, scopeType, createdAt, updatedAt);
		this.topLeftPosition = topLeftPosition;
		this.bottomRightPosition = bottomRightPosition;
    }
}
