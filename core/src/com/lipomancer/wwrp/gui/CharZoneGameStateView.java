package com.lipomancer.wwrp.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.lipomancer.wwrp.game.GameState;

/**
 * {@link GameStateView} object that draws the player's view.
 */
public class CharZoneGameStateView implements GameStateView {

    private static final int TILE_SIZE = 10;

    private final GameState gameState;

    /**
     * @param gameState The {@link GameState} to draw.
     */
    public CharZoneGameStateView(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int i = 0; i < gameState.getPlayer().getZone().width(); i++) {
            for (int j = 0; j < gameState.getPlayer().getZone().width(); j++) {
                shapeRenderer.setColor(Color.BROWN);
                shapeRenderer.rect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.circle(
                (gameState.getPlayer().getLocation().x + 0.5f) * TILE_SIZE,
                (gameState.getPlayer().getLocation().y + 0.5f) * TILE_SIZE,
                TILE_SIZE * 0.5f
        );

        shapeRenderer.end();
    }
}
