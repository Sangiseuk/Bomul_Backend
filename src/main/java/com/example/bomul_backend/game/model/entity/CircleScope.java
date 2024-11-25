package com.example.bomul_backend.game.model.entity;

import com.example.bomul_backend.common.Position;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class CircleScope extends Scope {
	private Position centerPosition;
	private double radius;

	public CircleScope(int scopeId,
	ScopeType scopeType,
	LocalDateTime createdAt,
	LocalDateTime updatedAt,
	Position centerPosition,
	double radius) {
		super(scopeId, scopeType, createdAt, updatedAt);
		this.centerPosition = centerPosition;
		this.radius = radius;
	}
}
