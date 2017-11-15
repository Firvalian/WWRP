package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.IntVector2;

/**
 * A mutable location within the game.
 */
public class Location {

    private World world;
    private IntVector2 worldPosition;
    private IntVector2 zonePosition;

    /**
     * @param world The {@link World} that contains this location
     * @param worldPosition The coordinates of the {@link WorldCell} of this location in its {@link World}
     * @param zonePosition The coordinates of the {@link ZoneCell} of this location in its {@link Zone}
     * @throws IllegalArgumentException if {@param worldPosition} is out of {@param world}'s bounds, or
     * if the {@param zonePosition} is out of the pointed {@link Zone}'s bounds.
     */
    public Location(World world, IntVector2 worldPosition, IntVector2 zonePosition) {
        if (!world.inBounds(worldPosition)) {
            throw new IllegalArgumentException("World coordinates are out of bounds");
        }
        if (!world.getCellAt(worldPosition).getZone().inBounds(zonePosition)) {
            throw new IllegalArgumentException("Zone coordinates are out of bounds.");
        }

        this.world = world;
        this.worldPosition = worldPosition;
        this.zonePosition = zonePosition;
    }

    public World getWorld() {
        return world;
    }

    public Zone getZone() {
        return world.getCellAt(this.worldPosition).getZone();
    }

    /**
     * @return The elevation of the {@link ZoneCell} this location is on.
     */
    public int getElevation() {
        return getZone().getCellAt(zonePosition).elevation();
    }

    public IntVector2 getWorldPosition() {
        return worldPosition;
    }

    public IntVector2 getZonePosition() {
        return zonePosition;
    }

    /**
     * @param delta the vector to translate the location by.
     * @throws IllegalArgumentException if the translation would go out of this location's {@link Zone}'s bounds.
     */
    public void translateInZone(IntVector2 delta) {
        IntVector2 newZonePosition = zonePosition.add(delta);
        if (!getZone().inBounds(newZonePosition)) {
            throw new IllegalArgumentException("Zone coordinates are out of bounds.");
        }

        this.zonePosition = newZonePosition;
    }
}
