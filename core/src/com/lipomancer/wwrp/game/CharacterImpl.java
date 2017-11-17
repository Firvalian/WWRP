package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.StepVector;

/**
 * The {@link Character} implementation.
 */
public class CharacterImpl extends BaseEntity implements Character {

    private final Location location;

    /**
     * Builds the character.
     *
     * @param location A {@link Location} designating the character's current location.
     */
    public CharacterImpl(Location location) {
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    /**
     * Checks to see if the location the Character attempts to move to is valid. If it is, moves the character and
     * returns true. If it isn't, it returns false.
     *
     * @param direction An IntVector2 designating the change of coordinates in x,y.
     * @return Whether the change of direction is valid.
     */
    @Override
    public boolean move(StepVector direction) {
        if (!location.getZone().movable(location.getZonePosition(), direction)){
            return false;
        } else {
            this.location.translateInZone(direction);
            return true;
        }
    }
}