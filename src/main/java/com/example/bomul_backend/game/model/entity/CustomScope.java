package com.example.bomul_backend.game.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.example.bomul_backend.common.Position;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CustomScope extends Scope {
	List<Position> customPoint;
	public CustomScope(int scopeId,
					   ScopeType scopeType,
					   LocalDateTime createdAt,
					   LocalDateTime updatedAt,
					   List<Position> customPoint) {
		super(scopeId, scopeType, createdAt, updatedAt);
		this.customPoint = customPoint;
	}
}
