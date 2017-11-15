package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.IntVector2;

import java.util.List;

/**
 * A grid navigable area, containing cells of type T.
 */
public interface Navigable<T> {

    /**
     * @return The width of this navigable.
     */
    int width();

    /**
     * @return The height of this navigable.
     */
    int height();

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
     * @throws IndexOutOfBoundsException if the coordinates are out of bounds
     */
    T getCellAt(int x, int y);

    /**
     * Gets the cell at the given location.
     *
     * @param position The coordinates of the cell
     * @return The cell at the given coordinates
     * @throws IndexOutOfBoundsException if the coordinates are out of bounds
     */
    default T getCellAt(IntVector2 position) {
        return getCellAt(position.x, position.y);
    }

    /**
     * Checks if the given coordinates are in bounds.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return Whether the given coordinates are out of bounds.
     */
    default boolean inBounds(int x, int y) {
        return x >= 0 &&
                y >= 0 &&
                x < width() &&
                y < height();
    }

    /**
     * Checks if the given coordinates are in bounds.
     *
     * @param position the coordinates to check.
     * @return Whether the given coordinates are out of bounds.
     */
    default boolean inBounds(IntVector2 position) {
        return inBounds(position.x, position.y);
    }
}
