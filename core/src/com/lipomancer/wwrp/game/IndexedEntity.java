package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.Property;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Entity that indexes the given set of its properties.
 */
public class IndexedEntity extends BaseEntity {

    private final List<String> indexKeys;
    private final Map<List<Object>, Entity> containedEntities;

    public IndexedEntity(Map<String, Object> properties, List<String> indexKeys, List<Entity> entities) {
        super(properties);
        this.indexKeys = indexKeys;
        this.containedEntities = new HashMap<>();
        entities.forEach(this::addEntity);
    }

    @Override
    public List<Entity> containedEntities() {
        return containedEntities.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public void addEntity(Entity entity) {
        List<Object> key = indexKeys.stream().map(k -> entity.properties().get(k).getValue().asObj()).collect(Collectors.toList());
        if (containedEntities.containsKey(key)) {
            throw new IllegalArgumentException("Sub-entity with the given keys exists: " + key.toString());
        }

        containedEntities.put(
                key,
                entity
        );
        entity.setParent(this);
    }

    @Override
    public Entity getContained(List<Property> properties) {
        List<String> keys = properties.stream().map(Property::getName).collect(Collectors.toList());
        if (!keys.equals(indexKeys)) {
            throw new UnsupportedOperationException("Given keys must be exactly the same and in the order of index keys.");
        }

        return containedEntities.get(properties.stream().map(p -> p.getValue().asObj()).collect(Collectors.toList()));
    }
}
