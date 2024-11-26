package com.example.bomul_backend.game.model.deserializer;

import com.example.bomul_backend.game.model.entity.ScopeType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class ScopeTypeDeserializer extends JsonDeserializer {
    @Override
    public ScopeType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String scopeType = node.get("scopeType").asText();
        return ScopeType.valueOf(scopeType);
    }
}
