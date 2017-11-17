package com.lipomancer.wwrp.game;

/**
 * Second iteration of the property-driven game state.
 */
public class GameState {

    private final EntityFactory entityFactory;
    private final Entity world;
    private final Entity player;

    public GameState(EntityFactory entityFactory, Entity world, Entity player) {
        this.entityFactory = entityFactory;
        this.world = world;
        this.player = player;
    }

    public Entity getWorld() {
        return world;
    }

    public Entity getPlayer() {
        return player;
    }

    public EntityFactory getEntityFactory() {
        return entityFactory;
    }
}
