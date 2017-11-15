package com.lipomancer.wwrp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.google.common.collect.ImmutableMap;
import com.lipomancer.wwrp.game.*;
import com.lipomancer.wwrp.game.prop.PrototypeStore;
import com.lipomancer.wwrp.gui.CharZoneGameStateView;
import com.lipomancer.wwrp.testdata.TestData;
import com.lipomancer.wwrp.util.IntVector2;
import com.lipomancer.wwrp.util.StepVector;

import java.util.Map;

public class WWRPGame extends ApplicationAdapter {

    private static final Map<Integer, StepVector> KEY_DIRECTIONS = ImmutableMap.of(
            Input.Keys.UP, StepVector.UP,
            Input.Keys.DOWN, StepVector.DOWN,
            Input.Keys.LEFT, StepVector.LEFT,
            Input.Keys.RIGHT, StepVector.RIGHT
    );

	private ShapeRenderer shapeRenderer;
	private GameState gameState;

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		gameState = TestData.getSampleData();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		for (Map.Entry<Integer, StepVector> entry : KEY_DIRECTIONS.entrySet()) {
		    if (Gdx.input.isKeyPressed(entry.getKey())) {
		        gameState.getPlayer().move(entry.getValue());
            }
        }

        new CharZoneGameStateView(gameState).draw(shapeRenderer);
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
