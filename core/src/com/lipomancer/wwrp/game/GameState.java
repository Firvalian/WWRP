package com.lipomancer.wwrp.game;

/**
 * Second iteration of the property-driven game state.
 */
public class GameState {

    private final Entity world;
    private final Entity player;

    public GameState(Entity world, Entity player) {
        this.world = world;
        this.player = player;
    }

    public Entity getWorld() {
        return world;
    }

    public Entity getPlayer() {
        return player;
    }
}
