package com.example.bomul_backend.game.model.entity;

import com.example.bomul_backend.common.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RectangleScope extends Scope {
	private Position topLeftPosition;
	private Position bottomRightPosition;
}
