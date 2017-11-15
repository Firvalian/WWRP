package com.lipomancer.wwrp.game.prop;

import java.util.Optional;
import java.util.Set;

/**
 * Factory for generating prototypes.
 */
public class PrototypeFactory {

    /**
     * Basic prototype returned by this factory.
     */
    private class BasicPrototype implements Prototype {

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
        public Optional<Object> defaultValue() {
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
    private class WithDefault implements Prototype {

        private final Prototype prototype;
        private final Object defaultValue;

        private WithDefault(Prototype prototype, Object defaultValue) {
            this.prototype = prototype;
            this.defaultValue = defaultValue;
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
        public Optional<Object> defaultValue() {
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
    private class WithSelection implements Prototype {

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
        public Optional<Object> defaultValue() {
            return prototype.defaultValue();
        }

        @Override
        public boolean accepts(Object value) {
            return acceptedValue.contains(value);
        }
    }
}
