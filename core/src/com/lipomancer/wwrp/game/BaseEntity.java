package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.Property;
import com.lipomancer.wwrp.game.prop.PrototypeStore;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract entity implementation for carrying properties.
 */
abstract class BaseEntity implements Entity {

    protected Entity parent;
    protected final Map<String, Property> properties;

    public BaseEntity(Map<String, Object> properties) {
        this(properties, NoEntity.INSTANCE);
    }

    public BaseEntity(Map<String, Object> properties, Entity parent) {
        this.parent = parent;
        this.properties = new HashMap<>();
        properties.forEach(this::setProperty);
    }

    @Override
    public Entity parent() {
        return parent;
    }

    @Override
    public Entity setParent(Entity newParent) {
        Entity oldParent = parent;
        parent = newParent;
        return oldParent;
    }

    @Override
    public boolean isNoEntity() {
        return false;
    }

    @Override
    public Map<String, Property> properties() {
        return properties;
    }

    @Override
    public void setProperty(String propertyName, Object propertyValue) {
        properties.put(propertyName, prototypeStore().get(propertyName).make(propertyValue));
    }

    @Override
    public PrototypeStore prototypeStore() {
        return PrototypeStore.getInstance();
    }

    @Override
    public String toString() {
        return String.format("{%s, %s}", properties().toString(), containedEntities().toString());
    }
}
