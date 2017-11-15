package com.lipomancer.wwrp.game.prop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    private Prototype stringProto;
    private Prototype numericProto;

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

    @BeforeEach
    void setUp() {
        stringProto = PrototypeFactory.makePrototype(PropertyType.STRING, "str");
        numericProto = PrototypeFactory.makePrototype(PropertyType.NUMERIC, "num");
    }
}