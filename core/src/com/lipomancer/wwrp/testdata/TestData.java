package com.lipomancer.wwrp.testdata;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.lipomancer.wwrp.game.*;
import com.lipomancer.wwrp.game.prop.PrototypeStore;

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


    private static final Supplier<GameState> INSTANCE = Suppliers.memoize(TestData::prepareGameState);

    public static GameState getSampleData() { return INSTANCE.get(); }

    private static GameState prepareGameState() {
        preparePrototypeStore();
        return new GameState(
                prepareWorld()
        );
    }

    private static Entity prepareWorld() {
        List<String> locIndices = ImmutableList.of("loc.x", "loc.y");
        return new IndexedEntity(
                ImmutableMap.of("type", "world"),
                locIndices,
                ImmutableList.of(
                    new IndexedEntity(
                            ImmutableMap.of(
                                    "type", "zone",
                                    "loc.x", 0,
                                    "loc.y", 0
                            ),
                            locIndices,
                            ImmutableList.of(
                                    new ListEntity(
                                            ImmutableMap.of(
                                                    "type", "zonecell",
                                                    "loc.x", 0,
                                                    "loc.y", 0
                                            )
                                    ),
                                    new ListEntity(
                                            ImmutableMap.of(
                                                    "type", "zonecell",
                                                    "loc.x", 0,
                                                    "loc.y", 1
                                            )
                                    ),
                                    new ListEntity(
                                            ImmutableMap.of(
                                                    "type", "zonecell",
                                                    "loc.x", 1,
                                                    "loc.y", 0
                                            )
                                    ),
                                    new ListEntity(
                                            ImmutableMap.of(
                                                    "type", "zonecell",
                                                    "loc.x", 1,
                                                    "loc.y", 1
                                            )
                                    )
                            )
                    )
                )
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
                                        "zonecell",
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
