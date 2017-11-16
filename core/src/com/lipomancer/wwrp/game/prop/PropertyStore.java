package com.lipomancer.wwrp.game.prop;

public interface PropertyStore {
    
    /**
     * Adds the given property to this object.
     *
     * @param name The name of the property.
     * @param value The value of the property.
     */
    void addProperty(String name, Object value);

    /**
     * Adds the given property to this property store. The validity of the property being constructed from the internal
     * prototype store is not checked.
     *
     * If the property already exists, it is overwritten.
     *
     * @param property the property to construct
     */
    void addProperty(Property property);
}
