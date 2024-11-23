package com.example.bomul_backend.game.model.entity;

import com.example.bomul_backend.common.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RectangleScope extends Scope {
	private Position topLeftPosition;
	private Position bottomRightPosition;
}
