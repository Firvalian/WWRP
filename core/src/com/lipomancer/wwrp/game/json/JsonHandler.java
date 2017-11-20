package com.lipomancer.wwrp.game.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lipomancer.wwrp.game.Entity;
import com.lipomancer.wwrp.game.EntityFactory;
import com.lipomancer.wwrp.game.prop.Property;
import com.lipomancer.wwrp.game.prop.PrototypeStore;

/**
 * Loads game data from JSON.
 */
public class JsonHandler {

    private final PrototypeStore prototypeStore;
    private final EntityFactory entityFactory;
    private final Config config;
    private final Gson gson;

    public JsonHandler(PrototypeStore prototypeStore, EntityFactory entityFactory) {
        this(prototypeStore, entityFactory, Config.getDefault());
    }

    /**
     * @param prototypeStore the {@link PrototypeStore} to be used for parsing properties.
     * @param entityFactory the {@link EntityFactory} to be used for constructing the parsed objects.
     * @param config the configuration parameters.
     */
    public JsonHandler(PrototypeStore prototypeStore, EntityFactory entityFactory, Config config) {
        this.prototypeStore = prototypeStore;
        this.entityFactory = entityFactory;
        this.config = config;
        this.gson = getGson();
    }

    /**
     * Loads the game data from JSON.
     *
     * @param json the JSON string to parse.
     * @return the game data.
     */
    public Entity fromJson(String json) {
        return gson.fromJson(json, Entity.class);
    }

    /**
     * @return the Gson object.
     */
    protected Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Entity.class, new EntityAdapter(entityFactory, config))
                .registerTypeAdapter(Property.class, new PropertyAdapter(entityFactory.getPrototypeStore(), config))
                .create();
    }

    /**
     * Configuration for {@link JsonHandler}.
     */
    public static class Config {

        String entityPropertiesKey;
        String entityContainedEntitiesKey;

        String propertyNameKey;
        String propertyValueKey;

        String typePropertyKey;

        public static Config getDefault() {
            return new Config()
                    .setEntityPropertiesKey("props")
                    .setEntityContainedEntitiesKey("ents")
                    .setPropertyNameKey("n")
                    .setPropertyValueKey("v")
                    .setTypePropertyKey("type");
        }


        public String getEntityPropertiesKey() {
            return entityPropertiesKey;
        }

        public String getEntityContainedEntitiesKey() {
            return entityContainedEntitiesKey;
        }

        public String getPropertyNameKey() {
            return propertyNameKey;
        }

        public String getPropertyValueKey() {
            return propertyValueKey;
        }

        public String getTypePropertyKey() {
            return typePropertyKey;
        }

        public Config setEntityPropertiesKey(String entityPropertiesKey) {
            this.entityPropertiesKey = entityPropertiesKey;
            return this;
        }

        public Config setEntityContainedEntitiesKey(String entityContainedEntitiesKey) {
            this.entityContainedEntitiesKey = entityContainedEntitiesKey;
            return this;
        }

        public Config setPropertyNameKey(String propertyNameKey) {
            this.propertyNameKey = propertyNameKey;
            return this;
        }

        public Config setPropertyValueKey(String propertyValueKey) {
            this.propertyValueKey = propertyValueKey;
            return this;
        }

        public Config setTypePropertyKey(String typePropertyKey) {
            this.typePropertyKey = typePropertyKey;
            return this;
        }
    }
}
