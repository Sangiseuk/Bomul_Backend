package com.example.bomul_backend.game.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.LocalDateTime;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "scopeType"
)
@JsonSubTypes({
		@JsonSubTypes.Type(value = CircleScope.class, name="CIRCLE"),
		@JsonSubTypes.Type(value = RectangleScope.class, name="RECTANGLE"),
		@JsonSubTypes.Type(value = CustomScope.class, name="CUSTOM")
})

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Scope {
	@Getter
    public enum ScopeType {
		CIRCLE(0),
		RECTANGLE(1),
		CUSTOM(2);

		private final int value;

		ScopeType(int value) {
			this.value = value;
		}

        public static ScopeType fromValue(int value) {
			return Arrays.stream(ScopeType.values())
					.filter(type -> type.value == value)
					.findFirst()
					.orElseThrow(() -> new IllegalArgumentException("Invalid scope type: " + value));
		}

		@JsonValue
		public String toJson(){
			return this.name();
		}


		@JsonCreator
		public static ScopeType fromJson(String name) {
			System.out.println("Received scopeType: " + name); // 디버깅용
			if (name == null || name.isEmpty()) {
				throw new IllegalArgumentException("ScopeType cannot be null or empty");
			}
			for (ScopeType type : ScopeType.values()) {
				if (type.name().equalsIgnoreCase(name)) {
					return type;
				}
			}
			throw new IllegalArgumentException("Invalid ScopeType: " + name);
		}
	}

    private int scopeId;
    private ScopeType scopeType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}