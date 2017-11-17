package com.lipomancer.wwrp.game;

import com.badlogic.gdx.utils.compression.lzma.Base;
import com.lipomancer.wwrp.game.prop.PrototypeStore;

import javax.swing.text.html.parser.Entity;

/**
 * Complete state of the game.
 */
public class GameState extends BaseEntity {

    private final World world;
    private final Character player;
    private final PrototypeStore prototypeStore;

    /**
     * @param world The game world.
     * @param player The player character.
     * @param prototypeStore The prototype store to use.
     */
    public GameState(World world, Character player, PrototypeStore prototypeStore) {
        this.world = world;
        this.player = player;
        this.prototypeStore = prototypeStore;
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

    /**
     * @return The prototype store for the game's prototypes.
     */
    public PrototypeStore getPrototypeStore() {
        return prototypeStore;
    }
}
