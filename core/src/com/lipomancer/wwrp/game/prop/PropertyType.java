package com.lipomancer.wwrp.game.prop;

import java.util.function.Function;

/**
 * Types of properties.
 */
public enum PropertyType {
    NUMERIC(ValueFactory::makeNumeric),
    STRING(ValueFactory::makeString),
    BOOLEAN(ValueFactory::makeBoolean),
    STRING_LIST(ValueFactory::makeStringList),
    NUMERIC_LIST(ValueFactory::makeNumericList);

    protected final Function<Object, PropertyValue> valueFrom;

    PropertyType(Function<Object, PropertyValue> valueFrom) {
        this.valueFrom = valueFrom;
    }
}
