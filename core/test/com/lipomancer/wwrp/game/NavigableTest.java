package com.lipomancer.wwrp.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NavigableTest {

    @Test
    public void testIndexOutOfBoundsGetCellAt() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> NavigableFactory.flatZone(1, 1).getCellAt(1, 1)
        );
    }

    @Test
    public void testInBounds() {
        assertTrue(NavigableFactory.flatZone(1, 1).inBounds(0, 0));
    }

    @Test
    public void testNotInBounds() {
        assertFalse(NavigableFactory.flatZone(3, 2).inBounds(2, 2));
    }
}