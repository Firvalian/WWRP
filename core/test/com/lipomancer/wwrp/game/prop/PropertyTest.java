package com.lipomancer.wwrp.game.prop;

import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    private Prototype stringProto;
    private Prototype numericProto;
    private Prototype selectProto;

    @Test
    public void testPropertyCreation() {
        Property property = stringProto.make("value");

        assertEquals("value", property.getValue().asString());
        assertEquals("str", property.getName());
        assertEquals(stringProto, property.getPrototype());
    }

    @Test
    public void testPropertyCreationTypeMismatchString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> stringProto.make(3.0)
        );
    }

    @Test
    public void testPropertyCreationTypeMismatchNumeric() {
        assertThrows(
                IllegalArgumentException.class,
                () -> numericProto.make("test")
        );
    }

    @Test
    public void testSelectMismatch() {
        assertThrows(
                IllegalArgumentException.class,
                () -> selectProto.make("c")
        );
    }

    @Test
    public void testSelectConstructionSuccess() {
        selectProto.make("a");
    }

    @BeforeEach
    void setUp() {
        stringProto = PrototypeFactory.makePrototype(PropertyType.STRING, "str");
        numericProto = PrototypeFactory.makePrototype(PropertyType.NUMERIC, "num");
        selectProto = PrototypeFactory.makeSelectionPrototype(PropertyType.STRING, "select", ImmutableSet.of("a", "b"));
    }
}