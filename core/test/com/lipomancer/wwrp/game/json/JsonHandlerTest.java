package com.lipomancer.wwrp.game.json;

import com.lipomancer.wwrp.game.Entity;
import com.lipomancer.wwrp.game.GameState;
import com.lipomancer.wwrp.testdata.TestData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonHandlerTest {

    private static GameState gameState;
    private JsonHandler jsonHandler;

    @Test
    void testBasic() {
        System.out.println(jsonHandler.fromJson(
                "{\"props\": [{\"n\": \"type\", \"v\": \"world\"}], \"ents\": []}"
        ));
    }

    @BeforeAll
    static void initialize() {
        gameState = TestData.prepareGameState();
    }

    @BeforeEach
    void setUp() {
        jsonHandler = new JsonHandler(
                gameState.getEntityFactory().getPrototypeStore(),
                gameState.getEntityFactory()
        );
    }
}