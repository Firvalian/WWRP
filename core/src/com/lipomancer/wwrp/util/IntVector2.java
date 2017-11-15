package com.lipomancer.wwrp.util;

import java.util.Objects;

/**
 * A two-dimensional integer vector.
 */
public class IntVector2 {

    public final int x;
    public final int y;

    /**
     * Gets the x and y values of this int vector.
     *
     * @param x The x value
     * @param y The y value
     */
    public IntVector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntVector2 that = (IntVector2) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
