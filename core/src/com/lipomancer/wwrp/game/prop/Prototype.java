package com.lipomancer.wwrp.game.prop;

import java.util.Optional;

/**
 * Prototype for properties.
 */
interface Prototype {

    /**
     * @return The name of the property.
     */
    String getName();
    /**
     * @return The type of the property.
     */
    PropertyType getType();

    /**
     * Creates a property from this prototype.
     *
     * @param value The value of the property.
     * @return The created property.
     */
    Property make(Object value);

    /**
     * @return The default value of this property.
     */
    Optional<Object> defaultValue();

    /**
     * Checks whether if the given parameter is a valid value for properties of this prototype.
     *
     * @param value The value to check
     * @return Whether if the value is accepted.
     */
    boolean accepts(Object value);
}
