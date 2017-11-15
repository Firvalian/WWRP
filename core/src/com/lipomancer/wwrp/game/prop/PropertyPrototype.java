package com.lipomancer.wwrp.game.prop;

import java.util.Optional;

/**
 * Prototypes for properties
 */
abstract class PropertyPrototype {

    private final PropertyType type;
    private final String name;

    protected PropertyPrototype(PropertyType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * @return The name of the property.
     */
    final String getName() {
        return name;
    }

    /**
     * @return The type of the property.
     */
    final PropertyType getType() {
        return type;
    }

    /**
     * Creates a property instance of this prototype.
     *
     * @param value The value of the property
     * @return The instantiated property.
     */
    final Property make(Object value) {
        if (!this.accepts(value)) {
            throw new IllegalArgumentException(String.format("%s is not accepted by %s", value.toString(), getName()));
        }

        return new Property(this, getType().valueFrom.apply(value));
    }

    /**
     * @return The default value of this property.
     */
    abstract Optional<Object> defaultValue();

    /**
     * Checks whether if instances of this property can have the given value. Override this for property prototypes
     * with limited value ranges.
     *
     * @param value the value to check
     * @return whether if the value is accepted.
     */
    abstract boolean accepts(Object value);
}
