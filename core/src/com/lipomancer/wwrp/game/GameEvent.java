package com.lipomancer.wwrp.game;

import java.util.function.Consumer;

/**
 * Describes an event that is run on a {@link com.lipomancer.wwrp.game.Entity}. The events change the game state.
 */
@FunctionalInterface
public interface GameEvent extends Consumer<Entity> {

}
