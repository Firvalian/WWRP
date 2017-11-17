package com.lipomancer.wwrp.game.prop;


import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for storing all prototypes available in game. This is expected to be a singleton.
 */
public class PrototypeStore {

    private static final PrototypeStore INSTANCE = new PrototypeStore();

    /**
     * @return a singleton instance for the prototype store.
     */
    public static PrototypeStore getInstance() {
        return INSTANCE;
    }

    private final Map<String, Prototype> prototypes;

    public PrototypeStore() {
        prototypes = new HashMap<>();
    }

    /**
     * Registers the given prototype.
     *
     * @param prototype The prototype to register.
     * @throws IllegalArgumentException if a prototype with the same name is already registered.
     * @return this
     */
    public PrototypeStore addPrototype(Prototype prototype) {
        Preconditions.checkArgument(
                !prototypes.containsKey(prototype.getName()),
                "Prototype with name %s already exists",
                prototype.getName()
        );
        prototypes.put(prototype.getName(), prototype);
        return this;
    }

    /**
     * Gets the prototype with the given name.
     *
     * @param name The name of the prototype
     * @return The prototype.
     */
    public Prototype get(String name) {
        return prototypes.get(name);
    }
}
