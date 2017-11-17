package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.PrototypeStore;

import java.util.*;

/**
 * Entity that contains the sub-entities in a list.
 */
public class ListEntity extends BaseEntity {

    private final List<Entity> containedEntities;

    public ListEntity(int id, PrototypeStore prototypeStore, Map<String, Object> properties, List<Entity> containedEntities) {
        super(id, prototypeStore, properties);
        this.containedEntities = new ArrayList<>();
        containedEntities.forEach(this::addEntity);
    }

    @Override
    public List<Entity> containedEntities() {
        return containedEntities;
    }

    @Override
    public void addEntity(Entity entity) {
        containedEntities.add(entity);
        entity.setParent(this);
    }

    @Override
    public Entity getContained(Map<String, Object> properties) {
        throw new UnsupportedOperationException();
    }
}
