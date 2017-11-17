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
                Number.class,
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

    static PropertyValue makeBoolean(Object value) {
        return make(
                value,
                Boolean.class,
                BooleanValue::new
        );
    }

    static PropertyValue makeStringList(Object value) {
        return makeList(
                value,
                String.class,
                StringListValue::new
        );
    }

    static PropertyValue makeNumericList(Object value) {
        return makeList(
                value,
                Number.class,
                NumericListValue::new
        );
    }

    private static <T> PropertyValue make(Object value, Class<T> valueClass, Function<T, PropertyValue> construction) {
        if (!(valueClass.isInstance(value))) {
            throw new IllegalArgumentException(String.format("Values of type %s expected", valueClass.getName()));
        }

        return construction.apply(valueClass.cast(value));
    }

    @SuppressWarnings("unchecked")
    private static <T> PropertyValue makeList(Object value, Class<T> valueClass, Function<List<T>, PropertyValue> construction) {
        if (!(value instanceof  List)) {
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
    private static abstract class BaseValue<T> implements PropertyValue {

        protected T value;

        BaseValue(T value) {
            this.value = value;
        }

        @Override
        public Object asObj() {
            return value;
        }

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
        public boolean asBoolean() {
            throwIllegalState();
            return false;
        }

        @Override
        public List<String> asStringList() {
            throwIllegalState();
            return null;
        }

        @Override
        public List<Number> asNumericList() {
            throwIllegalState();
            return null;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
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
    private static class NumericValue extends BaseValue<Number> {

        NumericValue(Number value) {
            super(value);
        }

        @Override
        public double asNumeric() {
            return value.doubleValue();
        }
    }

    /**
     * String values
     */
    private static class StringValue extends BaseValue<String> {

        StringValue(String value) {
            super(value);
        }

        @Override
        public String asString() {
            return value;
        }
    }

    /**
     * String values
     */
    private static class BooleanValue extends BaseValue<Boolean> {

        BooleanValue(Boolean value) {
            super(value);
        }

        @Override
        public boolean asBoolean() {
            return value;
        }
    }

    /**
     * String values
     */
    private static class StringListValue extends BaseValue<List<String>> {

        StringListValue(List<String> value) {
            super(value);
        }

        @Override
        public List<String> asStringList() {
            return value;
        }
    }

    /**
     * String values
     */
    private static class NumericListValue extends BaseValue<List<Number>> {

        NumericListValue(List<Number> value) {
            super(value);
        }

        @Override
        public List<Number> asNumericList() {
            return value;
        }
    }
}
