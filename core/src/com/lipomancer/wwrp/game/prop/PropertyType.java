package com.lipomancer.wwrp.game.prop;

import java.util.function.Function;

/**
 * Types of properties.
 */
public enum PropertyType {
    NUMERIC(ValueFactory::makeNumeric),
    STRING(ValueFactory::makeString),
    STRING_LIST(ValueFactory::makeStringList);

    protected final Function<Object, PropertyValue> valueFrom;

    PropertyType(Function<Object, PropertyValue> valueFrom) {
        this.valueFrom = valueFrom;
    }
}
