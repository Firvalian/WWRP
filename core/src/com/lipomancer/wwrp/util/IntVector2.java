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

    /**
     * Adds this and the given vectors.
     *
     * @param other The other vector to add.
     * @return The summation of the two vectors.
     */
    public IntVector2 add(IntVector2 other) {
        return new IntVector2(x + other.x, y + other.y);
    }

    /**
     * Multiplies this vector by the given factor.
     *
     * @param factor The coefficient.
     * @return The multiplied vector.
     */
    public IntVector2 mult(int factor) {
        return new IntVector2(x * factor, y * factor);
    }

    /**
     * @return A vector in opposite direction to this.
     */
    public IntVector2 opposite() {
        return mult(-1);
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
