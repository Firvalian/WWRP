package com.lipomancer.wwrp.game.prop;

import java.util.Optional;
import java.util.Set;

/**
 * Factory for generating prototypes.
 */
public class PrototypeFactory {

    /**
     * Creates a prototype with basic configurations.
     *
     * @param type The data type of the property.
     * @param name The name of value.
     * @return A basic prototype.
     */
    public static Prototype makePrototype(PropertyType type, String name) {
        return new BasicPrototype(type, name);
    }

    public static Prototype makeDefaultPrototype(PropertyType type, String name, Object defaultValue) {
        return new WithDefault(new BasicPrototype(type, name), defaultValue);
    }

    public static Prototype makeSelectionPrototype(PropertyType type, String name, Set<Object> acceptedValues) {
        return new WithSelection(new BasicPrototype(type, name), acceptedValues);
    }

    /**
     * Basic prototype returned by this factory.
     */
    private static class BasicPrototype implements Prototype {

        private final PropertyType type;
        private final String name;

        BasicPrototype(PropertyType type, String name) {
            this.type = type;
            this.name = name;
        }

        @Override
        public final String getName() {
            return name;
        }

        @Override
        public final PropertyType getType() {
            return type;
        }

        @Override
        public final Property make(Object value) {
            return new Property(this, getType().valueFrom.apply(value));
        }

        @Override
        public Optional<PropertyValue> defaultValue() {
            return Optional.empty();
        }

        @Override
        public boolean accepts(Object value) {
            return true;
        }
    }

    /**
     * Wrapper for adding a default into a prototype.
     */
    private static class WithDefault implements Prototype {

        private final Prototype prototype;
        private final PropertyValue defaultValue;

        private WithDefault(Prototype prototype, Object defaultValue) {
            this.prototype = prototype;
            this.defaultValue = prototype.getType().valueFrom.apply(defaultValue);
        }

        @Override
        public String getName() {
            return prototype.getName();
        }

        @Override
        public PropertyType getType() {
            return prototype.getType();
        }

        @Override
        public Property make(Object value) {
            return prototype.make(value);
        }

        @Override
        public Optional<PropertyValue> defaultValue() {
            return Optional.of(defaultValue);
        }

        @Override
        public boolean accepts(Object value) {
            return prototype.accepts(value);
        }
    }

    /**
     * Wrapper for adding a limited set of values for the given prototype.
     */
    private static class WithSelection implements Prototype {

        private final Prototype prototype;
        private final Set<Object> acceptedValue;

        private WithSelection(Prototype prototype, Set<Object> acceptedValue) {
            this.prototype = prototype;
            this.acceptedValue = acceptedValue;
        }

        @Override
        public String getName() {
            return prototype.getName();
        }

        @Override
        public PropertyType getType() {
            return prototype.getType();
        }

        @Override
        public Property make(Object value) {
            if (!accepts(value)) {
                throw new IllegalArgumentException(String.format("%s is not accepted by %s", value.toString(), getName()));
            }
            return prototype.make(value);
        }

        @Override
        public Optional<PropertyValue> defaultValue() {
            return prototype.defaultValue();
        }

        @Override
        public boolean accepts(Object value) {
            return acceptedValue.contains(value);
        }
    }
}
