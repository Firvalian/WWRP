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

    public Entity listEntity(Map<String, Object> properties) {
        return register(listEntity(properties, Collections.emptyList()));
    }

    public Entity listEntity(Map<String, Object> properties, List<Entity> containedEntities) {
        return register(new ListEntity(nextId(), prototypeStore, properties, containedEntities));
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

    private int nextId() {
        return counter.get();
    }

    private Entity register(Entity entity) {
        entities.put(entity.id(), entity);
        return entity;
    }
}
