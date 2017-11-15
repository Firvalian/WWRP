package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.IntVector2;
import org.junit.jupiter.api.Test;

import static com.lipomancer.wwrp.game.NavigableFactory.flatZone;
import static org.junit.jupiter.api.Assertions.*;


public class CharacterTest {

    @Test
    public void testFalseMovement(){
        Zone zone = flatZone(5, 5);
        IntVector2 location = new IntVector2(1,1);
        CharacterImpl PC = new CharacterImpl(zone, location);
        IntVector2 where = new IntVector2(-4, 42);
        assertFalse(PC.move(where));
    }

    @Test
    public void testTrueMovement(){
        Zone zone = flatZone(5, 5);
        IntVector2 location = new IntVector2(1,1);
        CharacterImpl PC = new CharacterImpl(zone, location);
        IntVector2 where = new IntVector2(2, 2);
        assertTrue(PC.move(where));
    }
}
