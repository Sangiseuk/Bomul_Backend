package com.example.bomul_backend.game.model.Entity;

public enum ScopeType {
    CIRCLE(0),
    RECTANGLE(1),
    CUSTOM(2);

    private int value;
    private ScopeType(int value) {
        this.value = value;
    }
}
