package com.example.bomul_backend.game.model.entity;

import com.example.bomul_backend.common.Position;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CircleScope extends Scope {
	private Position centerPosition;
	private double radius;

}
