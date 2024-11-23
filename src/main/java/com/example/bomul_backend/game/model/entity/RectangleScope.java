package com.example.bomul_backend.game.model.entity;

import com.example.bomul_backend.common.Position;
import lombok.Getter;

@Getter
public class RectangleScope extends Scope {
	private Position topLeftPosition;
	private Position bottomRightPosition;
}
