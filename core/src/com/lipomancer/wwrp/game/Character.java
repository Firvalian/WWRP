package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.IntVector2;

/**
 * A playable or a non-playable character
 */
public interface Character {

    boolean move(IntVector2 direction);

}
