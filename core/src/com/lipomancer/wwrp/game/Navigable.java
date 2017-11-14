package com.lipomancer.wwrp.game;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

/**
 * A navigable area, containing cells of type T.
 */
public interface Navigable<T> {

    /**
     * Gets the cells of this navigable as a square list. The returned list is in column-first fashion.
     *
     * @return List of lists depicting the cells of the navigable, in order. The first list is the columns, and the
     * second is the cells. All of the nested lists should have the same length.
     */
    List<List<T>> getCells();

    /**
     * Gets the cell at the given location.
     *
     * @param x The x coordinate of the cell
     * @param y The y coordinate of the cell
     * @return The cell at the given coordinates
     * @throws IllegalArgumentException if the coordinates are out of bounds
     */
    T getCellAt(int x, int y);
}
