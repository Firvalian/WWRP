package com.lipomancer.wwrp.util;

/**
 * Vector signifying one step in one of the cardinal directions.
 */
public class StepVector extends IntVector2 {

    public static final StepVector UP = new StepVector(0, 1);
    public static final StepVector DOWN = new StepVector(0, -1);
    public static final StepVector LEFT = new StepVector(-1, 0);
    public static final StepVector RIGHT = new StepVector(1, 0);

    /**
     * Gets the x and y values of this int vector.
     *
     * @param x The x value
     * @param y The y value
     */
    private StepVector(int x, int y) {
        super(x, y);
    }

    /**
     * @return Gets a step vector in opposite direction.
     */
    public StepVector oppositeAsStep() {
        IntVector2 asVec = opposite();
        return new StepVector(asVec.x, asVec.y);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
