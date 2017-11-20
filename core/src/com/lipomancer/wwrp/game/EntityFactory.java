package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.PrototypeStore;
import com.lipomancer.wwrp.util.Counter;

import java.util.*;

/**
 * Factory class for creating entities.
 */
public class EntityFactory {

    private final Counter counter;
    private final PrototypeStore prototypeStore;
    private final WeakHashMap<Integer, Entity> entities;

    public EntityFactory(PrototypeStore prototypeStore) {
        this.counter = new Counter();
        this.prototypeStore = prototypeStore;
        entities = new WeakHashMap<>();
        register(NoEntity.INSTANCE);
    }

    /**
     * @return a no-entity.
     */
    public Entity none() {
        return NoEntity.INSTANCE;
    }

    public Entity setEntity(Map<String, Object> properties) {
        return setEntity(properties, Collections.emptyList());
    }

    public Entity setEntity(Map<String, Object> properties, List<Entity> containedEntities) {
        return register(new SetEntity(nextId(), prototypeStore, properties, containedEntities));
    }

    public Entity indexEntity(Map<String, Object> properties, List<String> indexKeys, List<Entity> entities) {
        return register(new IndexedEntity(nextId(), prototypeStore, properties, indexKeys, entities));
    }

    /**
     * Gets an entity from an id.
     *
     * @param id the id of the entity to get.
     * @return the entity if found.
     */
    public Optional<Entity> fromId(int id) {
        return Optional.ofNullable(entities.get(id));
    }

    public PrototypeStore getPrototypeStore() {
        return prototypeStore;
    }

    private int nextId() {
        return counter.get();
    }

    private Entity register(Entity entity) {
        if (entities.containsKey(entity.id())) {
            throw new IllegalArgumentException("An entity with the provided entity's ID is present.");
        }
        entities.put(entity.id(), entity);
        return entity;
    }
}
