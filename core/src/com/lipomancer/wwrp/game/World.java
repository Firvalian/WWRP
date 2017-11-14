package com.lipomancer.wwrp.game;

import java.util.List;

/**
 * A navigable world, which contains the map-level view of the game world(s).
 */
public interface World {

    /**
     * Gets the cells of this world as a square list. The returned list is in column-first fashion.
     *
     * @return List of lists depicting the cells of the world, in order. The first list is the rows, and the second is
     * the cells. All of the nested lists should have the same length.
     */
    List<List<WorldCell>> getCells();
}
