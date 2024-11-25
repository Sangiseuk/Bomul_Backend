package com.example.bomul_backend.game.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.LocalDateTime;

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
	public enum ScopeType {
	    CIRCLE(0),
	    RECTANGLE(1),
	    CUSTOM(2);
		int value;
		ScopeType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static ScopeType fromValue(int value){
			for (ScopeType type : ScopeType.values()) {
				if(type.getValue() == value){
					return type;
				}
			}
			throw new IllegalArgumentException("Invalid scope type : " + value);
		}

		public ScopeType getByIndex(int index) {
	    	try {
	    		return ScopeType.values()[index];
	    	} catch (ArrayIndexOutOfBoundsException e) {
	    		throw new IllegalArgumentException("Invalid MarkerType Index : " + index);
	    	}
	    }
	}

    private int scopeId;
    private ScopeType scopeType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}