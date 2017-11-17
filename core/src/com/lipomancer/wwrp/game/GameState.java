package com.lipomancer.wwrp.game;

/**
 * Second iteration of the property-driven game state.
 */
public class GameState {

    private final Entity world;

    public GameState(Entity world) {
        this.world = world;
    }

    public Entity getWorld() {
        return world;
    }
}
