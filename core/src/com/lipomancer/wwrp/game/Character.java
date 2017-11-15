package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.IntVector2;
import com.lipomancer.wwrp.util.StepVector;

/**
 * A playable or a non-playable character
 */
public interface Character {

    /**
     * Gets the present zone of this character.
     *
     * @return The zone where the character is present.
     */
    Zone getZone();

    /**
     * @return The location of the given character in its zone.
     */
    IntVector2 getLocation();

    /**
     * Attempts to move the character in the given direction. The character doesn't move if the attempt is illegal.
     *
     * @param direction The direction for the character's movement.
     * @return Whether if the movement succeeded or not.
     */
    boolean move(StepVector direction);

}
