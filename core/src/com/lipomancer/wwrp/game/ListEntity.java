package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.Property;
import com.lipomancer.wwrp.game.prop.PrototypeStore;

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
        this(new ArrayList<>(), properties);
    }

    public ListEntity(List<Entity> containedEntities, Map<String, Object> properties) {
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
    public Entity getContained(List<Property> properties) {
        throw new UnsupportedOperationException();
    }
}
