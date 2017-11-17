package com.lipomancer.wwrp.game.prop;

import java.util.List;
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

    static PropertyValue makeStringList(Object value) {
        return make(
                value,
                List.class,
                StringListValue::new
        );
    }

    private static <T> PropertyValue make(Object value, Class<T> valueClass, Function<T, PropertyValue> construction) {
        if (!(value instanceof List)) {
            throw new IllegalArgumentException(String.format("Values of type %s expected", valueClass.getName()));
        }

        return construction.apply(valueClass.cast(value));
    }

    @SuppressWarnings("unchecked")
    private static <T> PropertyValue makeList(Object value, Class<T> valueClass, Function<List<T>, PropertyValue> construction) {
        if (!value.getClass().equals(List.class)) {
            throw new IllegalArgumentException("List expected.");
        }

        for (Object item : (List) value) {
            if (!item.getClass().equals(valueClass)) {
                throw new IllegalArgumentException(String.format("Values of type %s expected", valueClass.getName()));
            }
        }

        return construction.apply((List<T>) value);
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

        @Override
        public List<String> asStringList() {
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

    /**
     * String values
     */
    private static class StringListValue extends BaseValue {

        private List<String> value;

        StringListValue(List<String> value) {
            this.value = value;
        }

        @Override
        public List<String> asStringList() {
            return value;
        }
    }
}
