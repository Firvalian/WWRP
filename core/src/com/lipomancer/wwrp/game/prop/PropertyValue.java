package com.lipomancer.wwrp.game.prop;

import java.util.List;

/**
 * Value of the properties.
 */
public interface PropertyValue {

    /**
     * @return Gets the property as numeric.
     * @throws IllegalStateException if the value is not a numeric.
     */
    double asNumeric();

    /**
     * @return Gets the property as a string.
     * @throws IllegalStateException if the value is not a string.
     */
    String asString();
}
