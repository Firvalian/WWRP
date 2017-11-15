package com.lipomancer.wwrp.gui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Displays the given gamestate onto the screen.
 */
public interface GameStateView {

    /**
     * Draws the given {@link com.lipomancer.wwrp.game.GameState}.
     * @param shapeRenderer The {@link ShapeRenderer} to draw this {@link com.lipomancer.wwrp.game.GameState} onto.
     */
    void draw(ShapeRenderer shapeRenderer);
}
