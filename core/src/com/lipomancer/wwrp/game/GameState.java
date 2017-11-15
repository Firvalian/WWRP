package com.lipomancer.wwrp.game;

/**
 * Complete state of the game.
 */
public class GameState {

    private final World world;
    private final Character player;

    /**
     * @param world The game world.
     * @param player The player character.
     */
    public GameState(World world, Character player) {
        this.world = world;
        this.player = player;
    }

    /**
     * @return This game's world.
     */
    public World getWorld() {
        return world;
    }

    /**
     * @return This game's player.
     */
    public Character getPlayer() {
        return player;
    }
}
