package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.IntVector2;
import com.lipomancer.wwrp.util.StepVector;

/**
 * The locale-level navigable area.
 */
public interface Zone extends Navigable<ZoneCell> {

    /**
     * Checks if the movement in the given direction can be made from the given position.
     *
     * @param position The starting position.
     * @param direction The movement direction.
     * @return Whether if the given movement is permitted.
     */
    boolean movable(IntVector2 position, StepVector direction);
}
