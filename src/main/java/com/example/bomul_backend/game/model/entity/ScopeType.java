package com.example.bomul_backend.game.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
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

    public static ScopeType fromValue(int value) {
        for (ScopeType type : ScopeType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid scope type : " + value);
    }

    @JsonCreator
    public static ScopeType fromString(String value) {
        Logger logger = Logger.getLogger(ScopeType.class.getName());
        logger.log(Level.INFO, "Resolved ScopeType : {0}", value);
        return ScopeType.valueOf(value.toUpperCase());
    }

    @JsonValue
    @Override
    public String toString() {
        return this.name();
    }
}