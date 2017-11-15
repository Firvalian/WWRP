package com.lipomancer.wwrp.game;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.lipomancer.wwrp.util.IntVector2;
import com.lipomancer.wwrp.util.StepVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Factory class for generating navigables.
 */
public class NavigableFactory {

    /**
     * Generates a flat, empty zone.
     *
     * @param width The width of the zone.
     * @param height The height of the zone.
     * @return A flat zone.
     */
    public static Zone flatZone(int width, int height) {
        List<List<ZoneCell>> cells = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            cells.add(new ArrayList<>());
            for (int y = 0; y < height; y++) {
                cells.get(x).add(new ZoneCellImpl(0));
            }
        }
        return new ZoneImpl(cells);
    }

    /**
     * Generates a zone with elevations increasing without slope towards right.
     *
     * @param width The width of the zone.
     * @param height The height of the zone.
     * @return A flat zone.
     */
    public static Zone stratifiedZone(int width, int height) {
        List<List<ZoneCell>> cells = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            cells.add(new ArrayList<>());
            for (int y = 0; y < height; y++) {
                cells.get(x).add(new ZoneCellImpl(x));
            }
        }
        return new ZoneImpl(cells);
    }

    /**
     * Generates a zone with elevations increasing with slope towards right.
     *
     * @param width The width of the zone.
     * @param height The height of the zone.
     * @return A flat zone.
     */
    public static Zone slopedZone(int width, int height) {
        List<List<ZoneCell>> cells = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            cells.add(new ArrayList<>());
            for (int y = 0; y < height; y++) {
                cells.get(x).add(new ZoneCellImpl(x, ImmutableSet.of(StepVector.RIGHT)));
            }
        }
        return new ZoneImpl(cells);
    }

    /**
     * Generates a {@link World} with a single {@link WorldCell}, containing a the given zone.
     *
     * @param zone the singleton zone.
     * @return A world with a single flat zone.
     */
    public static World singletonWorld(Zone zone) {
        return new WorldImpl(
                ImmutableList.of(
                        ImmutableList.of(
                                new WorldCellImpl(zone)
                        )
                )
        );
    }

    /**
     * The {@link Navigable} implementation returned by this factory.
     *
     * @param <T> The navigable's cell type.
     */
    private static abstract class NavigableImpl<T> implements Navigable<T> {
        private final int width;
        private final int height;
        private final List<List<T>> cells;

        private NavigableImpl(List<List<T>> cells) {
            this.cells = cells;
            this.width = cells.isEmpty() ? 0 : cells.size();
            this.height = cells.isEmpty() || cells.get(0).isEmpty() ? 0 : cells.get(0).size();
        }

        @Override
        public int width() {
            return width;
        }

        @Override
        public int height() {
            return height;
        }

        @Override
        public List<List<T>> getCells() {
            return this.cells;
        }

        @Override
        public T getCellAt(int x, int y) {
            return this.cells.get(x).get(y);
        }
    }

    /**
     * The {@link ZoneCell} implementation returned by this factory.
     */
    private static class ZoneCellImpl implements ZoneCell {

        private final int elevation;
        private final Set<StepVector> slopes;

        /**
         * @param elevation Elevation of this cell.
         * @param slopes The directions towards which this cell has a rising slope.
         */
        private ZoneCellImpl(int elevation, Set<StepVector> slopes) {
            this.elevation = elevation;
            this.slopes = slopes;
        }

        /**
         * @param elevation Elevation of this cell.
         */
        private ZoneCellImpl(int elevation) {
            this(elevation, ImmutableSet.of());
        }

        @Override
        public boolean movable(StepVector direction, ZoneCell adjacentCell) {
            return elevation() == adjacentCell.elevation() ||
                    slopes().contains(direction) ||
                    adjacentCell.slopes().contains(direction.oppositeAsStep());
        }

        @Override
        public int elevation() {
            return elevation;
        }

        @Override
        public Set<StepVector> slopes() {
            return slopes;
        }
    }

    /**
     * The {@link Zone} implementation returned by this factory
     */
    private static class ZoneImpl extends NavigableImpl<ZoneCell> implements Zone {
        private ZoneImpl(List<List<ZoneCell>> cells) {
            super(cells);
        }

        @Override
        public boolean movable(IntVector2 position, StepVector direction) {
            IntVector2 newPosition = position.add(direction);

            return inBounds(newPosition) && getCellAt(position).movable(direction, getCellAt(newPosition));
        }
    }

    /**
     * The {@link WorldCell} implementation returned by this factory.
     */
    private static class WorldCellImpl implements WorldCell {

        private final Zone zone;

        /**
         * @param zone The {@link Zone} describing this world cell.
         */
        private WorldCellImpl(Zone zone) {
            this.zone = zone;
        }

        @Override
        public Zone getZone() {
            return zone;
        }
    }

    /**
     * The {@link World} implementation returned by this factory.
     */
    private static class WorldImpl extends NavigableImpl<WorldCell> implements World {
        private WorldImpl(List<List<WorldCell>> cells) {
            super(cells);
        }
    }
}
