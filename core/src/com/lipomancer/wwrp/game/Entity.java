package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.game.prop.Property;
import com.lipomancer.wwrp.game.prop.PrototypeStore;

import java.util.Collection;
import java.util.Map;

/**
 * Base game entity. All entities are driven by inclusion and properties. Therefore, each entity has a set of its
 * properties, and a set of included entities.
 */
public interface Entity {

    /**
     * @return the ID of this entity. ID of 0 is reserved for no-entities.
     */
    int id();

    /**
     * @return The entity that contains this entity.
     */
    Entity parent();

    /**
     * Sets the parent of this entity to the given entity. The operation removes this entity from its previous parent.
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
    Collection<Entity> containedEntities();

    /**
     * @param entity the entity to check
     * @return whether if this entity contains the given entity.
     */
    boolean contains(Entity entity);

    /**
     * Adds the given entity to this entity. This operation is not guaranteed to succeed, since some of the entities
     * might prohibit addition of multiple entities with the same properties, as in {@link IndexedEntity}.
     *
     * The operation sets the parent of the added entity to be this entity.
     *
     * @param entity The entity to add.
     */
    boolean addEntity(Entity entity);

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
    Entity getContained(Map<String, Object> properties);

    /**
     * Removes the given entity from the contained entities of this entity. Doesn't mutate the entity if the given entity
     * is not present.
     *
     * The given entity's parent will be set as {@link NoEntity} if it is currently set as this entity.
     *
     * @return whether if the entity was removed.
     */
    boolean remove(Entity entity);

    /**
     * Checks if this entity is the same entity with the given entity. Two entities are equal only if they are intensionally
     * equal.
     *
     * @param o the other object to check
     * @return whether if this entity is the same with the other entity.
     */
    @Override
    boolean equals(Object o);
}
