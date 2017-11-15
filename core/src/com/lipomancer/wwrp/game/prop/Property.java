package com.lipomancer.wwrp.game.prop;

/**
 * A single property.
 */
public class Property {

    private final PropertyPrototype proto;
    private final PropertyValue value;

    protected Property(PropertyPrototype proto, PropertyValue value) {
        this.proto = proto;
        this.value = value;
    }

    public PropertyPrototype getPrototype() {
        return proto;
    }

    public PropertyValue getValue() {
        return value;
    }
}
