package com.lipomancer.wwrp.testdata;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.lipomancer.wwrp.game.*;
import com.lipomancer.wwrp.game.Character;
import com.lipomancer.wwrp.game.prop.PrototypeFactory;
import com.lipomancer.wwrp.game.prop.PrototypeStore;
import com.lipomancer.wwrp.util.IntVector2;

import java.util.List;
import java.util.function.Supplier;

import static com.lipomancer.wwrp.game.prop.PropertyType.NUMERIC;
import static com.lipomancer.wwrp.game.prop.PropertyType.STRING;
import static com.lipomancer.wwrp.game.prop.PrototypeFactory.makeDefaultPrototype;
import static com.lipomancer.wwrp.game.prop.PrototypeFactory.makePrototype;
import static com.lipomancer.wwrp.game.prop.PrototypeFactory.makeSelectionPrototype;

/**
 * Test data examples for mocking the game.
 */
public class TestData {


    private static final Supplier<GameState2> INSTANCE = Suppliers.memoize(TestData::prepareGameState);

    public static GameState2 getSampleData() { return INSTANCE.get(); }

    private static GameState2 prepareGameState() {
        preparePrototypeStore();
        return new GameState2(
                prepareWorld()
        );
    }

    private static Entity prepareWorld() {
        List<String> locIndices = ImmutableList.of("loc.x", "loc.y");
        return new IndexedEntity(
                locIndices,
                ImmutableList.of(
                    new IndexedEntity(
                            locIndices,
                            ImmutableList.of(

                            ),
                            ImmutableMap.of(
                                    "type", "zone",
                                    "loc.x", 0,
                                    "loc.y", 0
                            )
                    )
                ),
                ImmutableMap.of("type", "world")
        );
    }

    private static PrototypeStore preparePrototypeStore() {
        return PrototypeStore.getInstance()
                .addPrototype(
                        makeSelectionPrototype(
                                STRING,
                                "type",
                                ImmutableSet.of(
                                        "world",
                                        "zone",
                                        "zonescell",
                                        "item",
                                        "itemproto",
                                        "character"
                                )
                        )
                ).addPrototype(
                        makePrototype(NUMERIC, "loc.x")
                ).addPrototype(
                        makePrototype(NUMERIC, "loc.y")
                ).addPrototype(
                        makeDefaultPrototype(NUMERIC, "loc.elevation", 0)
                );
    }
}
