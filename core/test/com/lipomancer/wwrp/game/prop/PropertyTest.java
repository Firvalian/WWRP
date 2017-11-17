package com.lipomancer.wwrp.game.prop;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    private Prototype stringProto;
    private Prototype numericProto;
    private Prototype booleanProto;
    private Prototype selectProto;
    private Prototype stringListProto;

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

    @Test
    public void testListProperty() {
        assertEquals(
                stringListProto.make(ImmutableList.of("a", "b")).getValue().asStringList(),
                ImmutableList.of("a", "b")
        );
    }

    @Test
    public void testBoolProperty() {
        assertFalse(
                booleanProto.make(false).getValue().asBoolean()
        );
    }

    @BeforeEach
    void setUp() {
        stringProto = PrototypeFactory.makePrototype(PropertyType.STRING, "str");
        numericProto = PrototypeFactory.makePrototype(PropertyType.NUMERIC, "num");
        booleanProto = PrototypeFactory.makePrototype(PropertyType.BOOLEAN, "bool");
        selectProto = PrototypeFactory.makeSelectionPrototype(PropertyType.STRING, "select", ImmutableSet.of("a", "b"));
        stringListProto = PrototypeFactory.makePrototype(PropertyType.STRING_LIST, "stringlist");
    }
}