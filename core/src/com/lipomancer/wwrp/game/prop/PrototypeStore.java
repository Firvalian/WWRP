package com.lipomancer.wwrp.game.prop;


import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class about all properties available in game. This is expected to be a singleton.
 */
public class PrototypeStore {

    private static final PrototypeStore INSTANCE = new PrototypeStore();
    private final Map<String, Prototype> properties;


    public PrototypeStore() {
        properties = new HashMap<>();
    }

    /**
     * @return The singleton instance.
     */
    public static PrototypeStore getInstance() {
        return INSTANCE;
    }
}
