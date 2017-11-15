package com.lipomancer.wwrp.game.prop;


import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class about all properties available in game.
 */
public class Properties {

    private static final Properties INSTANCE = new Properties();
    private final Map<String, PropertyPrototype> properties;


    public Properties() {
        properties = new HashMap<>();
    }

    /**
     * @return The singleton instance.
     */
    public static Properties getInstance() {
        return INSTANCE;
    }
}
