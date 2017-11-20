package com.lipomancer.wwrp.game.json;

import com.google.common.collect.ImmutableMap;
import com.google.gson.*;
import com.lipomancer.wwrp.game.prop.Property;
import com.lipomancer.wwrp.game.prop.PropertyType;
import com.lipomancer.wwrp.game.prop.Prototype;
import com.lipomancer.wwrp.game.prop.PrototypeStore;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PropertyAdapter implements JsonSerializer<Property>, JsonDeserializer<Property> {

    private static final Map<PropertyType, Function<JsonElement, Object>> valueExtractor = ImmutableMap.<PropertyType, Function<JsonElement, Object>>builder()
            .put(PropertyType.STRING, JsonElement::getAsString)
            .put(PropertyType.NUMERIC, JsonElement::getAsDouble)
            .put(PropertyType.BOOLEAN, JsonElement::getAsBoolean)
            .put(PropertyType.NUMERIC_LIST, e -> Util.stream(e.getAsJsonArray()).map(JsonElement::getAsDouble).collect(Collectors.toList()))
            .put(PropertyType.STRING_LIST, e -> Util.stream(e.getAsJsonArray()).map(JsonElement::getAsDouble).collect(Collectors.toList()))
            .build();
    private final PrototypeStore prototypeStore;
    private final JsonHandler.Config config;

    public PropertyAdapter(PrototypeStore prototypeStore, JsonHandler.Config config) {
        this.prototypeStore = prototypeStore;
        this.config = config;
    }

    @Override
    public Property deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        Prototype prototype = prototypeStore.get(object.get(config.getPropertyNameKey()).getAsString());

        return prototype.make(
                propertyValueOf(
                        prototype.getType(),
                        object.get(config.getPropertyValueKey()),
                        context
                )
        );
    }

    /**
     * Deserializes the given JSON element into Java object based on its provided property type.
     *
     * @param propertyType the type of the property
     * @param json the JSON object to deserialize
     * @return the parsed value of the property.
     */
    private Object propertyValueOf(PropertyType propertyType, JsonElement json, JsonDeserializationContext context) {
        return valueExtractor.get(propertyType).apply(json);
    }

    @Override
    public JsonElement serialize(Property src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
