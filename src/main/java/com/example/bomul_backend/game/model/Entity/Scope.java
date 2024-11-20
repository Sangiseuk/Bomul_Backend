package com.example.bomul_backend.game.model.Entity;

import java.time.LocalDateTime;

import com.example.bomul_backend.game.model.Entity.Scope.ScopeType;

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