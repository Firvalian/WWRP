package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.Property;
import com.lipomancer.wwrp.game.prop.PrototypeStore;

import java.util.List;
import java.util.Map;

/**
 * Base game entity. All entities are driven by inclusion and properties. Therefore, each entity has a set of its
 * properties, and a set of included entities.
 */
public interface Entity {

    /**
     * @return the entities contained by this entity.
     */
    List<Entity> containedEntities();

    /**
     * @return The properties of this entity.
     */
    Map<String, Property> properties();

    /**
     * Sets the given property for this entity. If the property already exists, the property is overwritten. The
     * property's prototype is acquired from {@link com.lipomancer.wwrp.game.prop.PrototypeStore}.
     *
     * @param propertyName the property's name.
     * @param propertyValue the property's value.
     */
    void setProperty(String propertyName, Object propertyValue);

    /**
     * Gets the prototype store that is used for initializing the properties of this object.
     *
     * @return the prototype store used by this object.
     */
    PrototypeStore prototypeStore();
}
