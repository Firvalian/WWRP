package com.lipomancer.wwrp.testdata;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.lipomancer.wwrp.game.*;
import com.lipomancer.wwrp.game.prop.PrototypeStore;

import java.util.List;

import static com.lipomancer.wwrp.game.prop.PropertyType.*;
import static com.lipomancer.wwrp.game.prop.PrototypeFactory.makeDefaultPrototype;
import static com.lipomancer.wwrp.game.prop.PrototypeFactory.makePrototype;
import static com.lipomancer.wwrp.game.prop.PrototypeFactory.makeSelectionPrototype;

/**
 * Test data examples for mocking the game.
 */
public class TestData {

    public static GameState prepareGameState() {
        EntityFactory entityFactory = new EntityFactory(preparePrototypeStore());
        return new GameState(
                entityFactory,
                prepareWorld(entityFactory),
                preparePlayer(entityFactory)
        );
    }

    private static Entity prepareWorld(EntityFactory ef) {
        List<String> locIndices = ImmutableList.of("loc.x", "loc.y");
        return ef.indexEntity(
                ImmutableMap.of(
                        "type", "world",
                        "loc.width", 1,
                        "loc.height", 1
                ),
                locIndices,
                ImmutableList.of(
                    ef.indexEntity(
                            ImmutableMap.of(
                                    "type", "zone",
                                    "loc.x", 0,
                                    "loc.y", 0,
                                    "loc.width", 2,
                                    "loc.height", 2
                            ),
                            locIndices,
                            ImmutableList.of(
                                    ef.setEntity(
                                            ImmutableMap.of(
                                                    "type", "zonecell",
                                                    "loc.x", 0,
                                                    "loc.y", 0,
                                                    "loc.elevation", 0
                                            )
                                    ),
                                    ef.setEntity(
                                            ImmutableMap.of(
                                                    "type", "zonecell",
                                                    "loc.x", 0,
                                                    "loc.y", 1,
                                                    "loc.elevation", 0
                                            )
                                    ),
                                    ef.setEntity(
                                            ImmutableMap.of(
                                                    "type", "zonecell",
                                                    "loc.x", 1,
                                                    "loc.y", 0,
                                                    "loc.elevation", 0
                                            )
                                    ),
                                    ef.setEntity(
                                            ImmutableMap.of(
                                                    "type", "zonecell",
                                                    "loc.x", 1,
                                                    "loc.y", 1,
                                                    "loc.elevation", 0
                                            )
                                    )
                            )
                    )
                )
        );
    }

    private static Entity preparePlayer(EntityFactory ef) {
        return ef.setEntity(
                ImmutableMap.of(
                        "player", true
                )
        );
    }

    private static PrototypeStore preparePrototypeStore() {
        return new PrototypeStore()
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
                        makeDefaultPrototype(BOOLEAN, "player", false)
                ).addPrototype(
                        makePrototype(NUMERIC, "loc.width")
                ).addPrototype(
                        makePrototype(NUMERIC, "loc.height")
                ).addPrototype(
                        makePrototype(NUMERIC, "loc.x")
                ).addPrototype(
                        makePrototype(NUMERIC, "loc.y")
                ).addPrototype(
                        makePrototype(NUMERIC, "dx")
                ).addPrototype(
                        makePrototype(NUMERIC, "dy")
                ).addPrototype(
                        makeDefaultPrototype(NUMERIC, "loc.elevation", 0)
                ).addPrototype(
                        makePrototype(NUMERIC_LIST, "vec2")
                );
    }
}
