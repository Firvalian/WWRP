package com.lipomancer.wwrp.game;

import com.google.common.collect.ImmutableList;
import com.lipomancer.wwrp.game.prop.Property;

import java.util.List;
import java.util.function.Consumer;

/**
 * Describes a game action that is run on a {@link com.lipomancer.wwrp.game.Entity}. The events change the game state.
 */
@FunctionalInterface
public interface Action {

    /**
     * Applies the given action to the given target, with the provided parameters. The action is permitted to change the
     * game state.
     *
     * @param target the target to apply the function on.
     * @param params the parameters of the action, passed in as a list of properties.
     */
    void apply(Entity target, Entity params);

}
