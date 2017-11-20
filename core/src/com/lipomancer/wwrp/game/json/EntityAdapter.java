package com.lipomancer.wwrp.game.json;

import com.google.gson.*;
import com.lipomancer.wwrp.game.Entity;
import com.lipomancer.wwrp.game.EntityFactory;
import com.lipomancer.wwrp.game.prop.Property;
import com.lipomancer.wwrp.game.prop.PropertyValue;

import java.lang.reflect.Type;
import java.util.stream.Collectors;

class EntityAdapter implements JsonDeserializer<Entity>, JsonSerializer<Entity> {

    private final EntityFactory entityFactory;
    private final JsonHandler.Config config;

    EntityAdapter(EntityFactory entityFactory, JsonHandler.Config config) {
        this.entityFactory = entityFactory;
        this.config = config;
    }

    @Override
    public Entity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        return entityFactory.setEntity(
                Util.deserializeListOf(object.get(config.getEntityPropertiesKey()), Property.class, context).stream()
                    .collect(Collectors.toMap(
                            Property::getName,
                            property -> property.getValue().asObj()
                    )),
                Util.deserializeListOf(object.get(config.getEntityContainedEntitiesKey()), Entity.class, context)
        );
    }

    @Override
    public JsonElement serialize(Entity src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();

        JsonObject propertiesMap = new JsonObject();
        src.properties().forEach(
                (name, property) -> propertiesMap.add(
                        name,
                        context.serialize(property.getValue(), PropertyValue.class)
                )
        );

        JsonArray containedList = new JsonArray();
        src.containedEntities().forEach(e -> containedList.add(context.serialize(e, Entity.class)));

        result.add(config.getEntityPropertiesKey(), propertiesMap);
        result.add(config.getEntityContainedEntitiesKey(), containedList);

        return result;
    }
}
