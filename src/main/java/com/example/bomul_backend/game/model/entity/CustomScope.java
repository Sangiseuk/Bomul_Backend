package com.example.bomul_backend.game.model.entity;

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

}
