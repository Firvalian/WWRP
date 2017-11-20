package com.lipomancer.wwrp.game.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Utilities for Json handling.
 */
class Util {

    static <T> List<T> deserializeListOf(JsonElement json, Class<T> typeOfT, JsonDeserializationContext deserializationContext) {
        List<T> result = new ArrayList<>();
        json.getAsJsonArray().forEach(e -> result.add(deserializationContext.deserialize(e, typeOfT)));
        return result;
    }

    static Stream<JsonElement> stream(JsonArray array) {
        return StreamSupport.stream(array.spliterator(), false);
    }
}
