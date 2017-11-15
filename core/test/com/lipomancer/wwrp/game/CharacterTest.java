package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.IntVector2;
import com.lipomancer.wwrp.util.StepVector;
import org.junit.jupiter.api.Test;

import static com.lipomancer.wwrp.game.NavigableFactory.flatZone;
import static org.junit.jupiter.api.Assertions.*;


public class CharacterTest {

    @Test
    public void testFalseMovement(){
        Character character = new CharacterImpl(flatZone(5, 5), new IntVector2(0, 1));
        assertFalse(character.move(StepVector.LEFT));
    }

    @Test
    public void testTrueMovement(){
        Character character = new CharacterImpl(flatZone(5, 5), new IntVector2(0, 1));
        assertTrue(character.move(StepVector.RIGHT));
    }
}
