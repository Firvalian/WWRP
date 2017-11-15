package com.lipomancer.wwrp.game;

import com.badlogic.gdx.math.Vector2;

public class CharacterImpl implements Character {

    private Zone zone;
    private Vector2 location;

    public CharacterImpl(Zone zone, Vector2 location){
        this.zone = zone;
        this.location = location;
    }

    @Override
    public boolean move(Vector2 direction) {
        boolean success;

        float x = location.x + direction.x;
        float y = location.y = direction.y;

        if (zone.getCellAt((int) x,(int) y) == null){
            success = false;
        } else {
            success = true;
        }

        return success;
    }
}
