package com.lipomancer.wwrp.game.prop;

import java.util.HashMap;
import java.util.Map;

/**
 * Holder for properties. This is the base class for implementing property-driven game entities.
 */
public abstract class PropertyStore {

    private final PrototypeStore prototypeStore;
    private final Map<String, Property> properties;

    /**
     * Creates a property store with no properties.
     * @param prototypeStore the prototype store.
     */
    public PropertyStore(PrototypeStore prototypeStore) {
        this(new HashMap<>(), prototypeStore);
    }

    /**
     * Creates a property store with the given property map.
     *
     * @param properties The map for properties.
     */
    private PropertyStore(Map<String, Property> properties, PrototypeStore prototypeStore) {
        this.properties = properties;
        this.prototypeStore = prototypeStore;
    }

    /**
     * Adds the given property to this object.
     *
     * @param name The name of the property.
     * @param value The value of the property.
     */
    public void addProperty(String name, Object value) {
        addProperty(prototypeStore.get(name).make(value));
    }

    /**
     * Adds the given property to this property store. The validity of the property being constructed from the internal
     * prototype store is not checked.
     *
     * If the property already exists, it is overwritten.
     *
     * @param property the property to construct
     */
    public void addProperty(Property property) {
        properties.put(property.getName(), property);
    }
}
