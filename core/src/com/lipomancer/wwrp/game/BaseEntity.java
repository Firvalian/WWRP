package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.Property;
import com.lipomancer.wwrp.game.prop.PrototypeStore;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Abstract entity implementation for carrying properties.
 */
abstract class BaseEntity implements Entity {

    private final int id;
    protected final PrototypeStore prototypeStore;
    protected Entity parent;
    protected final Map<String, Property> properties;

    public BaseEntity(int id, PrototypeStore prototypeStore, Map<String, Object> properties) {
        this(id, prototypeStore, properties, NoEntity.INSTANCE);
    }

    public BaseEntity(int id, PrototypeStore prototypeStore, Map<String, Object> properties, Entity parent) {
        this.prototypeStore = prototypeStore;
        this.id = id;
        this.parent = parent;
        this.properties = new HashMap<>();
        properties.forEach(this::setProperty);
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public Entity parent() {
        return parent;
    }

    @Override
    public Entity setParent(Entity newParent) {
        if (newParent.equals(parent)) {
            return parent;
        }

        Entity oldParent = parent;
        if (oldParent.contains(this)) {
            oldParent.remove(this);
        }

        parent = newParent;
        if (!newParent.contains(this)) {
            newParent.addEntity(this);
        }

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
        return prototypeStore;
    }

    @Override
    public String toString() {
        return String.format(
                Locale.getDefault(),
                "E#%d{properties=%s, contained=%s}",
                id(),
                properties().values().toString(),
                containedEntities().toString()
        );
    }
}
