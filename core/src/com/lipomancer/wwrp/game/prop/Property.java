package com.lipomancer.wwrp.game.prop;

/**
 * A single property.
 */
public class Property {

    private final Prototype proto;
    private final PropertyValue value;

    protected Property(Prototype proto, PropertyValue value) {
        this.proto = proto;
        this.value = value;
    }

    public Prototype getPrototype() {
        return proto;
    }

    public String getName(){
        return proto.getName();
    }

    public PropertyValue getValue() {
        return value;
    }

    public String stringValue() { return getValue().asString(); }

    @Override
    public String toString() {
        return String.format("(%s, %s)", getName(), getValue());
    }
}
