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

    public String getProtoName(){
        return proto.getName();
    }

    public PropertyValue getValue() {
        return value;
    }
}
