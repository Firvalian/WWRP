package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.IntVector2;
import com.lipomancer.wwrp.util.StepVector;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.lipomancer.wwrp.game.NavigableFactory.flatZone;
import static com.lipomancer.wwrp.game.NavigableFactory.singletonWorld;
import static org.junit.jupiter.api.Assertions.*;


public class CharacterTest {

    private World basicWorld;
    private Character basicCharacter;


    @Test
    public void testFalseMovement(){
        assertFalse(basicCharacter.move(StepVector.LEFT));
        assertEquals(basicCharacter.getLocation().getZonePosition(), new IntVector2(0, 0));
    }

    @Test
    public void testTrueMovement(){
        assertTrue(basicCharacter.move(StepVector.RIGHT));
        assertEquals(basicCharacter.getLocation().getZonePosition(), new IntVector2(1, 0));
    }

    @Test
    public void testMovementSequenceSquare() {
        assertTrue(basicCharacter.move(StepVector.RIGHT));
        assertTrue(basicCharacter.move(StepVector.UP));
        assertTrue(basicCharacter.move(StepVector.LEFT));
        assertTrue(basicCharacter.move(StepVector.DOWN));
        assertEquals(basicCharacter.getLocation().getZonePosition(), new IntVector2(0, 0));
    }

    @BeforeEach
    void setUp() {
        basicWorld = singletonWorld(flatZone(5, 5));
        basicCharacter = new CharacterImpl(
                new Location(
                        basicWorld,
                        new IntVector2(0, 0),
                        new IntVector2(0, 0)
                )
        );
    }
}
