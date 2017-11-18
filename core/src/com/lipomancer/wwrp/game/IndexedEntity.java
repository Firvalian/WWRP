package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.PrototypeStore;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Entity that indexes the given set of its properties.
 */
public class IndexedEntity extends BaseEntity {

    private final List<String> indexKeys;
    private final Map<List<Object>, Entity> containedEntities;

    IndexedEntity(int id, PrototypeStore prototypeStore, Map<String, Object> properties, List<String> indexKeys, List<Entity> entities) {
        super(id, prototypeStore, properties);
        this.indexKeys = indexKeys;
        this.containedEntities = new HashMap<>();
        entities.forEach(this::addEntity);
    }

    @Override
    public Collection<Entity> containedEntities() {
        return containedEntities.values();
    }

    @Override
    public boolean contains(Entity entity) {
        return Optional.ofNullable(containedEntities.get(keysOf(entity))).map(entity::equals).orElse(false);
    }

    @Override
    public boolean addEntity(Entity entity) {
        List<Object> key = keysOf(entity);
        if (containedEntities.containsKey(key)) {
            throw new IllegalArgumentException("Sub-entity with the given keys exists: " + key.toString());
        }

        containedEntities.put(
                key,
                entity
        );
        entity.setParent(this);

        return true;
    }

    @Override
    public Entity getContained(Map<String, Object> properties) {
        return containedEntities.get(indexKeys.stream().map(properties::get).collect(Collectors.toList()));
    }

    @Override
    public boolean remove(Entity entity) {
        boolean result = containedEntities.remove(
                keysOf(entity),
                entity
        );

        if (entity.parent().equals(this)) {
            entity.setParent(NoEntity.INSTANCE);
        }

        return result;
    }

    /**
     * Gets the keys to be used for the internal indexing, from the given entity.
     *
     * @param entity the entity to extract the keys from
     * @return the list of index keys
     */
    private List<Object> keysOf(Entity entity) {
        return indexKeys.stream().map(k -> entity.properties().get(k).getValue().asObj()).collect(Collectors.toList());
    }
}
