package com.example.bomul_backend.game.model.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"
)
@JsonSubTypes({
		@JsonSubTypes.Type(value = CircleScope.class, name="circle"),
		@JsonSubTypes.Type(value = RectangleScope.class, name="rectangle"),
		@JsonSubTypes.Type(value = CustomScope.class, name="custom")
})

@Getter
public class Scope {
	public enum ScopeType {
	    CIRCLE,
	    RECTANGLE,
	    CUSTOM;

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