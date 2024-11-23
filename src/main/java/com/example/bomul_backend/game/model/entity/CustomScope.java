package com.example.bomul_backend.game.model.entity;

import java.util.List;

import com.example.bomul_backend.common.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomScope extends Scope {
	private List<Position> customPoint;

}
