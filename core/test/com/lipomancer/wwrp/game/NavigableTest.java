package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.IntVector2;
import com.lipomancer.wwrp.util.StepVector;
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

    @Test
    public void testMovableCannotChangeElevations() {
        assertFalse(
                NavigableFactory.stratifiedZone(2, 1).movable(new IntVector2(0, 0), StepVector.RIGHT)
        );
    }

    @Test
    public void testMovableSameElevations() {
        assertTrue(
                NavigableFactory.flatZone(2, 1).movable(new IntVector2(0, 0), StepVector.RIGHT)
        );
    }
}