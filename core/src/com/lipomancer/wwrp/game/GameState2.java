package com.lipomancer.wwrp.game;

/**
 * Second iteration of the property-driven game state.
 */
public class GameState2 {

    private final Entity world;

    public GameState2(Entity world) {
        this.world = world;
    }

    public Entity getWorld() {
        return world;
    }
}
