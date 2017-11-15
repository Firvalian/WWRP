package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.IntVector2;
import com.lipomancer.wwrp.util.StepVector;

/**
 * The {@link Character} implementation.
 */
public class CharacterImpl implements Character {

    private Zone zone;
    private IntVector2 location;
    /**
     * Builds the character.
     *
     * @param location An IntVector2 designating the character's current location.
     * @param zone A {@link Zone} that the character is in.
     */
    public CharacterImpl(Zone zone, IntVector2 location) {
        this.zone = zone;
        this.location = location;
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
        int x = location.x + direction.x;
        int y = location.y + direction.y;

        if (!zone.inBounds(x, y)){
            return false;
        } else {
            this.location = new IntVector2(x, y);
            return true;
        }
    }
}