package com.lipomancer.wwrp.game.prop;

/**
 * A single property.
 */
public class Property {

    private final String name;
    private final PropertyValue value;

    public Property(String name, PropertyValue value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public PropertyValue getValue() {
        return value;
    }
}
