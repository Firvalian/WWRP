package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.IntVector2;
import com.lipomancer.wwrp.util.StepVector;

/**
 * A playable or a non-playable character
 */
public interface Character {

    /**
     * @return The location of the given character in its zone.
     */
    Location getLocation();

    /**
     * Attempts to move the character in the given direction. The character doesn't move if the attempt is illegal.
     *
     * @param direction The direction for the character's movement.
     * @return Whether if the movement succeeded or not.
     */
    boolean move(StepVector direction);

}
