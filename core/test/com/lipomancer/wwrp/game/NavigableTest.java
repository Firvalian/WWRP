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
    public void dummyTest() {
        assertNotEquals(false, true);
    }
}