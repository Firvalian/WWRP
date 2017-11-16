package com.lipomancer.wwrp.game.prop;

import java.util.HashMap;
import java.util.Map;

/**
 * Holder for properties. This is the base class for implementing property-driven game entities.
 */
public abstract class BasePropertyStore implements PropertyStore {

    private final PrototypeStore prototypeStore;
    private final Map<String, Property> properties;

    /**
     * Creates a property store with no properties.
     * @param prototypeStore the prototype store.
     */
    public BasePropertyStore(PrototypeStore prototypeStore) {
        this(new HashMap<>(), prototypeStore);
    }

    /**
     * Creates a property store with the given property map.
     *
     * @param properties The map for properties.
     */
    private BasePropertyStore(Map<String, Property> properties, PrototypeStore prototypeStore) {
        this.properties = properties;
        this.prototypeStore = prototypeStore;
    }

    @Override
    public void addProperty(String name, Object value) {
        addProperty(prototypeStore.get(name).make(value));
    }

    @Override
    public void addProperty(Property property) {
        properties.put(property.getName(), property);
    }
}
