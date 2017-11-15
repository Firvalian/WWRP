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
        PrototypeStore prototypeStore = new PrototypeStore();
        prototypeStore.addPrototype(
                PrototypeFactory.makePrototype(PropertyType.STRING, "property1")
        );
        prototypeStore.addPrototype(
                PrototypeFactory.makeSelectionPrototype(
                        PropertyType.STRING,
                        "selection1",
                        ImmutableSet.of("item1", "item2")
                )
        );
        prototypeStore.addPrototype(
                PrototypeFactory.makeDefaultPrototype(
                        PropertyType.NUMERIC,
                        "gain",
                        0.0
                )
        );
        return prototypeStore;
    }
}
