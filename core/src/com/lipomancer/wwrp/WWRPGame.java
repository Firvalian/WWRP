package com.lipomancer.wwrp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.google.common.collect.ImmutableMap;
import com.lipomancer.wwrp.game.*;
import com.lipomancer.wwrp.testdata.TestData;

import java.util.Map;

public class WWRPGame extends ApplicationAdapter {

	private ShapeRenderer shapeRenderer;
	private GameState gameState;
	private Map<Integer, Entity> moveParams;

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		gameState = TestData.prepareGameState();
		moveParams = ImmutableMap.of(
                Input.Keys.UP, gameState.getEntityFactory().setEntity(ImmutableMap.of("dx", 0, "dy", 1)),
                Input.Keys.DOWN, gameState.getEntityFactory().setEntity(ImmutableMap.of("dx", 0, "dy", -1)),
                Input.Keys.LEFT, gameState.getEntityFactory().setEntity(ImmutableMap.of("dx", -1, "dy", 0)),
                Input.Keys.RIGHT, gameState.getEntityFactory().setEntity(ImmutableMap.of("dx", 1, "dy", 0))
        );
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (Map.Entry<Integer, Entity> entry : moveParams.entrySet()) {
            if (Gdx.input.isKeyPressed(entry.getKey())) {
                ((Action) (target, params) -> {
                    Entity zoneCell = target.parent();
                    int newX = (int) (params.properties().get("dx").getValue().asNumeric() + zoneCell.properties().get("loc.x").getValue().asNumeric());
                    int newY = (int) (params.properties().get("dy").getValue().asNumeric() + zoneCell.properties().get("loc.y").getValue().asNumeric());
                    Entity zone = zoneCell.parent();

                    if (newX >= 0 &&
                            newY >= 0 &&
                            newX < zone.properties().get("loc.width").getValue().asNumeric() &&
                            newY < zone.properties().get("loc.height").getValue().asNumeric()) {
                        zoneCell.containedEntities().remove(target);
                        zone.getContained(ImmutableMap.of("loc.x", newX, "loc.y", newY)).addEntity(target);
                    }

                    System.out.println("Player cell: " + gameState.getPlayer().parent().toString());
                }).apply(gameState.getPlayer(), entry.getValue());
            }
        }
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
