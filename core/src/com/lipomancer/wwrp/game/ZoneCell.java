package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.StepVector;

import java.util.Set;

/**
 * One cell of a Zone
 */
public interface ZoneCell {

    /**
     * Checks if a movement from this cell can be made to the given adjacent cell, which is in the given direction.
     *
     * @param direction The direction the given cell is adjacent from.
     * @param adjacentCell The adjacent cell.
     * @return Whether if the movement is permitted.
     */
    boolean movable(StepVector direction, ZoneCell adjacentCell);

    /**
     * @return Gets the elevation of this cell.
     */
    int elevation();

    /**
     * @return The directions towards which this tile has a rising slope.
     */
    Set<StepVector> slopes();
}
