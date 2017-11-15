package com.lipomancer.wwrp.game;

import java.util.ArrayList;
import java.util.List;

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
                cells.get(x).add(new ZoneCellImpl());
            }
        }
        return new ZoneImpl(cells);
    }

    /**
     * The {@link ZoneCell} implementation returned by this factory.
     */
    private static class ZoneCellImpl implements ZoneCell {

    }

    /**
     * The {@link Zone} implementation returned by this factory
     */
    private static class ZoneImpl implements Zone {
        private final int width;
        private final int height;
        private final List<List<ZoneCell>> cells;

        private ZoneImpl(List<List<ZoneCell>> cells) {
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
        public List<List<ZoneCell>> getCells() {
            return this.cells;
        }

        @Override
        public ZoneCell getCellAt(int x, int y) {
            return this.cells.get(x).get(y);
        }
    }
}
