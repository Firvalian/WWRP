package com.lipomancer.wwrp.game.prop;

import java.util.List;

/**
 * Value of the properties.
 */
public interface PropertyValue {

    /**
     * @return Gets the property value as an {@link Object}.
     */
    Object asObj();

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

    /**
     * @return Gets the property as a boolean.
     * @throws IllegalStateException if the property is not a boolean.
     */
    boolean asBoolean();

    /**
     * @return the property value as a numeric list
     * @throws IllegalStateException if the property is not a numeric
     */
    List<Number> asNumericList();

    /**
     * @return gets the property as a string list.
     * @throws IllegalArgumentException if the value is not a string list.
     */
    List<String> asStringList();
}
