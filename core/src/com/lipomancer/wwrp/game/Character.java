package com.lipomancer.wwrp.game;

import com.badlogic.gdx.math.Vector2;

/**
 * A playable or a non-playable character
 */
public interface Character {

    boolean move(Vector2 direction);

}
