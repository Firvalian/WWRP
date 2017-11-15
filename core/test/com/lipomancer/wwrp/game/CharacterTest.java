package com.lipomancer.wwrp.game;

import com.lipomancer.wwrp.util.IntVector2;
import com.lipomancer.wwrp.util.StepVector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.lipomancer.wwrp.game.NavigableFactory.*;
import static org.junit.jupiter.api.Assertions.*;


public class CharacterTest {

    private World basicWorld;
    private Character basicWorldCharacter;

    private World stratifiedWorld;
    private Character stratifiedWorldCharacter;

    private World slopedWorld;
    private Character slopedWorldCharacter;


    @Test
    public void testFalseMovement(){
        assertFalse(basicWorldCharacter.move(StepVector.LEFT));
        assertEquals(basicWorldCharacter.getLocation().getZonePosition(), new IntVector2(0, 0));
    }

    @Test
    public void testTrueMovement(){
        assertTrue(basicWorldCharacter.move(StepVector.RIGHT));
        assertEquals(basicWorldCharacter.getLocation().getZonePosition(), new IntVector2(1, 0));
    }

    @Test
    public void testMovementSequenceSquare() {
        assertTrue(basicWorldCharacter.move(StepVector.RIGHT));
        assertTrue(basicWorldCharacter.move(StepVector.UP));
        assertTrue(basicWorldCharacter.move(StepVector.LEFT));
        assertTrue(basicWorldCharacter.move(StepVector.DOWN));
        assertEquals(basicWorldCharacter.getLocation().getZonePosition(), new IntVector2(0, 0));
    }

    @Test
    public void testMovementElevationNoSlope() {
        assertFalse(stratifiedWorldCharacter.move(StepVector.RIGHT));
        assertTrue(stratifiedWorldCharacter.move(StepVector.UP));
        assertFalse(stratifiedWorldCharacter.move(StepVector.RIGHT));
    }

    @Test
    public void testMovementElevationSlope() {
        assertTrue(slopedWorldCharacter.move(StepVector.RIGHT));
        assertTrue(slopedWorldCharacter.move(StepVector.RIGHT));
        assertTrue(slopedWorldCharacter.move(StepVector.LEFT));
    }

    @BeforeEach
    void setUp() {
        basicWorld = singletonWorld(flatZone(5, 5));
        basicWorldCharacter = new CharacterImpl(
                new Location(
                        basicWorld,
                        new IntVector2(0, 0),
                        new IntVector2(0, 0)
                )
        );

        stratifiedWorld = singletonWorld(stratifiedZone(5, 5));
        stratifiedWorldCharacter = new CharacterImpl(
                new Location(
                        stratifiedWorld,
                        new IntVector2(0, 0),
                        new IntVector2(0, 0)
                )
        );

        slopedWorld = singletonWorld(slopedZone(5, 5));
        slopedWorldCharacter = new CharacterImpl(
                new Location(
                        slopedWorld,
                        new IntVector2(0, 0),
                        new IntVector2(0, 0)
                )
        );
    }
}
