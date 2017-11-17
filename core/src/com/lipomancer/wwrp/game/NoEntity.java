package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.Property;
import com.lipomancer.wwrp.game.prop.PrototypeStore;

import java.util.Collection;
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
        return PrototypeStore.getInstance();
    }

    @Override
    public Entity getContained(List<Property> properties) {
        throw new UnsupportedOperationException();
    }
}
