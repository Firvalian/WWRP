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
     * @return The entity that contains this entity.
     */
    Entity parent();

    /**
     * Sets the parent of this entity to the given entity.
     *
     * @param newParent the new parent
     * @return the old parent
     */
    Entity setParent(Entity newParent);

    /**
     * Checks if this entity is no-entity, implemented as {@link NoEntity}. That entity works as null.
     *
     * @return whether if this entity is a no-entity.
     */
    boolean isNoEntity();

    /**
     * @return the entities contained by this entity.
     */
    List<Entity> containedEntities();


    /**
     * Adds the given entity to this entity. This operation is not guaranteed to succeed, since some of the entities
     * might prohibit addition of multiple entities with the same properties, as in {@link IndexedEntity}.
     *
     * @param entity The entity to add.
     */
    void addEntity(Entity entity);

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

    /**
     * Gets the contained entity with the given property. Returns any of those if the there are multiple entities with
     * such property.
     *
     * @param  properties the properties to check in the sub-item
     * @return the entity with the given property equal to the given value.
     * @throws UnsupportedOperationException if the operation is not implemented.
     */
    Entity getContained(List<Property> properties);


}
