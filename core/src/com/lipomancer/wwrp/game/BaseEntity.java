package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.Property;
import com.lipomancer.wwrp.game.prop.PrototypeStore;

import java.util.*;

/**
 * Base implementation of the game's entities.
 */
public class BaseEntity implements Entity {

    private final List<Entity> containedEntities;
    private final Map<String, Property> properties;

    public BaseEntity() {
        this(new ArrayList<>(), new HashMap<>());
    }

    public BaseEntity(List<Entity> containedEntities, Map<String, Property> properties) {
        this.containedEntities = containedEntities;
        this.properties = properties;
    }

    @Override
    public List<Entity> containedEntities() {
        return containedEntities;
    }

    @Override
    public Map<String, Property> properties() {
        return Collections.unmodifiableMap(properties);
    }

    @Override
    public void setProperty(String propertyName, Object propertyValue) {
        properties.put(propertyName, prototypeStore().get(propertyName).make(propertyValue));
    }

    @Override
    public PrototypeStore prototypeStore() {
        return PrototypeStore.getInstance();
    }
}
