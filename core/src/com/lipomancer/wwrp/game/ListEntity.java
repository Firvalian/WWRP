package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.Property;

import java.util.*;

/**
 * Entity that contains the sub-entities in a list.
 */
public class ListEntity extends BaseEntity {

    private final List<Entity> containedEntities;

    public ListEntity() {
        this(new HashMap<>());
    }

    public ListEntity(Map<String, Object> properties) {
        this(properties, new ArrayList<>());
    }

    public ListEntity(Map<String, Object> properties, List<Entity> containedEntities) {
        super(properties);
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
