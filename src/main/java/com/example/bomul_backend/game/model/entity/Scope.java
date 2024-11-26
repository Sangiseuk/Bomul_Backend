package com.example.bomul_backend.game.model.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "jsonScopeType"
)
@JsonSubTypes({
		@JsonSubTypes.Type(value = CircleScope.class, name="CIRCLE"),
		@JsonSubTypes.Type(value = RectangleScope.class, name="RECTANGLE"),
		@JsonSubTypes.Type(value = CustomScope.class, name="CUSTOM")
})

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public abstract class Scope {
    private int scopeId;
    private ScopeType scopeType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}