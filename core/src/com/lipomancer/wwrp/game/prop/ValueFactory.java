package com.lipomancer.wwrp.game.prop;

import java.util.function.Function;

/**
 * Factory class for creating values.
 */
class ValueFactory {

    static PropertyValue makeNumeric(Object value) {
        return make(
                value,
                Double.class,
                NumericValue::new
        );
    }

    static PropertyValue makeString(Object value) {
        return make(
                value,
                String.class,
                StringValue::new
        );
    }

    private static <T> PropertyValue make(Object value, Class<T> valueClass, Function<T, PropertyValue> construction) {
        if (!value.getClass().equals(valueClass)) {
            throw new IllegalArgumentException(String.format("Values of type %s expected", valueClass.getName()));
        }

        return construction.apply(valueClass.cast(value));
    }

    /**
     * Base class of produced values.
     */
    private static abstract class BaseValue implements PropertyValue {

        @Override
        public double asNumeric() {
            throwIllegalState();
            return 0;
        }

        @Override
        public String asString() {
            throwIllegalState();
            return null;
        }

        /**
         * @throws IllegalStateException with message noting the given type access.
         */
        private void throwIllegalState() {
            throw new IllegalStateException(
                    String.format("Operation not permitted for %s", getClass().getName())
            );
        }
    }

    /**
     * Numeric values
     */
    private static class NumericValue extends BaseValue {

        private double value;

        NumericValue(double value) {
            this.value = value;
        }

        @Override
        public double asNumeric() {
            return value;
        }
    }

    /**
     * String values
     */
    private static class StringValue extends BaseValue {

        private String value;

        StringValue(String value) {
            this.value = value;
        }

        @Override
        public String asString() {
            return value;
        }
    }
}
