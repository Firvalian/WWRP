package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.Property;
import com.lipomancer.wwrp.game.prop.PrototypeStore;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A null entity for stubbing the parent link of the entity.
 */
public final class NoEntity implements Entity {

    public static NoEntity INSTANCE = new NoEntity();

    private NoEntity() { }

    @Override
    public int id() {
        return 0;
    }

    @Override
    public Entity parent() {
        return this;
    }

    @Override
    public Entity setParent(Entity newParent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNoEntity() {
        return true;
    }

    @Override
    public List<Entity> containedEntities() {
        return Collections.emptyList();
    }

    @Override
    public void addEntity(Entity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Property> properties() {
        return Collections.emptyMap();
    }

    @Override
    public void setProperty(String propertyName, Object propertyValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PrototypeStore prototypeStore() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Entity getContained(Map<String, Object> properties) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "#NoEntity#";
    }
}
