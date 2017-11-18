package com.lipomancer.wwrp.game;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.lipomancer.wwrp.game.prop.PrototypeStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.lipomancer.wwrp.game.prop.PropertyType.*;
import static com.lipomancer.wwrp.game.prop.PrototypeFactory.makePrototype;
import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    private EntityFactory entityFactory;

    @Test
    void basicConstruction() {
        entityFactory.listEntity(
                ImmutableMap.of(
                        "numeric", 3
                )
        );
    }

    @Test
    void uniqueId() {
        assertNotEquals(
                entityFactory.listEntity(ImmutableMap.of("numeric", 3)).id(),
                entityFactory.listEntity(ImmutableMap.of("numeric", 3)).id()
        );
    }

    @Test
    void uniqueIdWithNone() {
        assertNotEquals(
                NoEntity.INSTANCE.id(),
                entityFactory.listEntity(ImmutableMap.of("numeric", 3)).id()
        );
    }

    @Test
    void fromId() {
        Entity entity = entityFactory.listEntity(ImmutableMap.of("numeric", 3));
        assertSame(
                entity,
                entityFactory.fromId(entity.id()).orElse(NoEntity.INSTANCE)
        );
    }

    @Test
    void indexRejectsDuplicate() {
        Entity entity = entityFactory.indexEntity(
                ImmutableMap.of(),
                ImmutableList.of("string"),
                ImmutableList.of(
                        entityFactory.listEntity(
                                ImmutableMap.of(
                                        "string", "hey"
                                )
                        )
                )
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> entity.addEntity(
                        entityFactory.listEntity(
                                ImmutableMap.of(
                                        "string", "hey"
                                )
                        )
                )
        );
    }

    @Test
    void indexTakesSemiDuplicate() {
        Entity entity = entityFactory.indexEntity(
                ImmutableMap.of(),
                ImmutableList.of("string", "numeric"),
                ImmutableList.of(
                        entityFactory.listEntity(
                                ImmutableMap.of(
                                        "string", "hey",
                                        "numeric", 1
                                )
                        )
                )
        );
        entity.addEntity(
                entityFactory.listEntity(
                        ImmutableMap.of(
                                "string", "hey",
                                "numeric", 2
                        )
                )
        );
    }

    @Test
    void indexRejectsDuplicateMultiKey() {
        Entity entity = entityFactory.indexEntity(
                ImmutableMap.of(),
                ImmutableList.of("string", "numeric"),
                ImmutableList.of(
                        entityFactory.listEntity(
                                ImmutableMap.of(
                                        "string", "hey",
                                        "numeric", 1,
                                        "bool", true
                                )
                        )
                )
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> entity.addEntity(
                        entityFactory.listEntity(
                                ImmutableMap.of(
                                        "string", "hey",
                                        "numeric", 1,
                                        "bool", false
                                )
                        )
                )
        );
    }

    @Test
    void parentSetOnInclusion() {
        Entity entity = entityFactory.listEntity(ImmutableMap.of("numeric", 3));
        assertSame(entity.parent(), NoEntity.INSTANCE);
        Entity parent = entityFactory.listEntity(
                ImmutableMap.of("numeric", 3),
                ImmutableList.of(entity)
        );
        assertSame(parent, entity.parent());
    }

    @BeforeEach
    void setUp() {
        PrototypeStore prototypeStore = new PrototypeStore()
                .addPrototype(
                        makePrototype(NUMERIC, "numeric")
                ).addPrototype(
                        makePrototype(BOOLEAN, "bool")
                ).addPrototype(
                        makePrototype(STRING, "string")
                );
        entityFactory = new EntityFactory(prototypeStore);
    }
}