package com.example.bomul_backend.game.model.entity;

import java.util.List;

import com.example.bomul_backend.common.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomScope extends Scope {
	List<Position> customPoint;

}
