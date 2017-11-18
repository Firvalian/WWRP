package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.PrototypeStore;

import java.util.*;

/**
 * Entity that contains the sub-entities in a list.
 */
public class SetEntity extends BaseEntity {

    private final Set<Entity> containedEntities;

    SetEntity(int id, PrototypeStore prototypeStore, Map<String, Object> properties, Collection<Entity> containedEntities) {
        super(id, prototypeStore, properties);
        this.containedEntities = new HashSet<>();
        containedEntities.forEach(this::addEntity);
    }

    @Override
    public Collection<Entity> containedEntities() {
        return containedEntities;
    }

    @Override
    public boolean contains(Entity entity) {
        return containedEntities.contains(entity);
    }

    @Override
    public boolean addEntity(Entity entity) {
        boolean result = containedEntities.add(entity);
        entity.setParent(this);
        return result;
    }

    @Override
    public Entity getContained(Map<String, Object> properties) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Entity entity) {
        boolean result = containedEntities.remove(entity);

        if (entity.parent().equals(this)) {
            entity.setParent(NoEntity.INSTANCE);
        }

        return result;
    }
}
