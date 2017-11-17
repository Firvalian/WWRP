package com.lipomancer.wwrp.testdata;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSet;
import com.lipomancer.wwrp.game.*;
import com.lipomancer.wwrp.game.Character;
import com.lipomancer.wwrp.game.prop.PropertyType;
import com.lipomancer.wwrp.game.prop.PrototypeFactory;
import com.lipomancer.wwrp.game.prop.PrototypeStore;
import com.lipomancer.wwrp.util.IntVector2;

import java.util.function.Supplier;

import static com.lipomancer.wwrp.game.prop.PropertyType.NUMERIC;
import static com.lipomancer.wwrp.game.prop.PropertyType.STRING;

/**
 * Test data examples for mocking the game.
 */
public class TestData {

    private static final Supplier<GameState> INSTANCE = Suppliers.memoize(TestData::prepareGameState);

    public static GameState getSampleData() {
        return INSTANCE.get();
    }

    private static GameState prepareGameState() {
        World world = prepareWorld();
        return new GameState(
                world,
                prepareCharacter(world),
                preparePrototypeStore()
        );
    }

    private static World prepareWorld() {
        return NavigableFactory.singletonWorld(
                NavigableFactory.slopedZone(50, 50)
        );
    }

    private static Character prepareCharacter(World world) {
        return new CharacterImpl(
                new Location(
                        world,
                        new IntVector2(0, 0),
                        new IntVector2(0, 0)
                )
        );
    }

    private static PrototypeStore preparePrototypeStore() {
        return new PrototypeStore()
                .addPrototype(
                        PrototypeFactory.makeSelectionPrototype(
                                STRING,
                                "type",
                                ImmutableSet.of(
                                        "item",
                                        "itemproto",
                                        "character"
                                )
                        )
                );
    }
}
