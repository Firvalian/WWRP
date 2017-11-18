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
        entityFactory.setEntity(
                ImmutableMap.of(
                        "numeric", 3
                )
        );
    }

    @Test
    void uniqueId() {
        assertNotEquals(
                entityFactory.setEntity(ImmutableMap.of("numeric", 3)).id(),
                entityFactory.setEntity(ImmutableMap.of("numeric", 3)).id()
        );
    }

    @Test
    void uniqueIdWithNone() {
        assertNotEquals(
                NoEntity.INSTANCE.id(),
                entityFactory.setEntity(ImmutableMap.of("numeric", 3)).id()
        );
    }

    @Test
    void fromId() {
        Entity entity = entityFactory.setEntity(ImmutableMap.of("numeric", 3));
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
                        entityFactory.setEntity(
                                ImmutableMap.of(
                                        "string", "hey"
                                )
                        )
                )
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> entity.addEntity(
                        entityFactory.setEntity(
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
                        entityFactory.setEntity(
                                ImmutableMap.of(
                                        "string", "hey",
                                        "numeric", 1
                                )
                        )
                )
        );
        entity.addEntity(
                entityFactory.setEntity(
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
                        entityFactory.setEntity(
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
                        entityFactory.setEntity(
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
    void parentSetNoop() {
        Entity entity = entityFactory.setEntity(ImmutableMap.of("numeric", 3));
        Entity parent = entityFactory.setEntity(
                ImmutableMap.of("numeric", 3),
                ImmutableList.of(entity)
        );
        entity.setParent(parent);
        assertSame(parent, entity.parent());
        assertTrue(parent.contains(entity));
    }

    @Test
    void parentSetOnInclusion() {
        Entity entity = entityFactory.setEntity(ImmutableMap.of("numeric", 3));
        assertSame(entity.parent(), NoEntity.INSTANCE);
        Entity parent = entityFactory.setEntity(
                ImmutableMap.of("numeric", 3),
                ImmutableList.of(entity)
        );
        assertSame(parent, entity.parent());
    }

    @Test
    void inclusionOnParentSet() {
        Entity entity = entityFactory.setEntity(ImmutableMap.of("numeric", 3));
        assertSame(entity.parent(), NoEntity.INSTANCE);
        Entity parent = entityFactory.setEntity(
                ImmutableMap.of("numeric", 3)
        );
        entity.setParent(parent);
        assertSame(parent, entity.parent());
        assertTrue(parent.contains(entity));
    }

    @Test
    void parentUnsetOnRemoval() {
        Entity child = entityFactory.setEntity(ImmutableMap.of("numeric", 3));
        Entity parent = entityFactory.setEntity(
                ImmutableMap.of("numeric", 3),
                ImmutableList.of(child)
        );
        parent.remove(child);
        assertSame(NoEntity.INSTANCE, child.parent());
        assertFalse(parent.contains(child));
    }

    @Test
    void removalOnParentUnset() {
        Entity child = entityFactory.setEntity(ImmutableMap.of("numeric", 3));
        Entity parent = entityFactory.setEntity(
                ImmutableMap.of("numeric", 3),
                ImmutableList.of(child)
        );
        child.setParent(NoEntity.INSTANCE);
        assertSame(NoEntity.INSTANCE, child.parent());
        assertFalse(parent.contains(child));
    }

    @Test
    void switchParentAddition() {
        Entity child = entityFactory.setEntity(ImmutableMap.of("numeric", 3));
        Entity oldParent = entityFactory.setEntity(
                ImmutableMap.of("numeric", 3),
                ImmutableList.of(child)
        );
        Entity newParent = entityFactory.setEntity(
                ImmutableMap.of("numeric", 3)
        );
        newParent.addEntity(child);
        assertSame(newParent, child.parent());
        assertFalse(oldParent.contains(child));
        assertTrue(newParent.contains(child));
    }

    @Test
    void switchParentSet() {
        Entity child = entityFactory.setEntity(ImmutableMap.of("numeric", 3));
        Entity oldParent = entityFactory.setEntity(
                ImmutableMap.of("numeric", 3),
                ImmutableList.of(child)
        );
        Entity newParent = entityFactory.setEntity(
                ImmutableMap.of("numeric", 3)
        );
        child.setParent(newParent);
        assertSame(newParent, child.parent());
        assertFalse(oldParent.contains(child));
        assertTrue(newParent.contains(child));
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