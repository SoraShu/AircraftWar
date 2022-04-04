package edu.hitsz.aircraft;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    private HeroAircraft instance;

    @BeforeEach
    void setUp() {
        instance = HeroAircraft.getHeroAircraft();
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    @ParameterizedTest
    @DisplayName("Test method decreaseHp")
    @ValueSource(ints = {10,50,110})
    void decreaseHp(int num) {
        int lastHp = instance.getHp();
        instance.decreaseHp(num);
        if (num <= lastHp) {
            assertEquals(lastHp - num,instance.getHp());
        }
        else {
            assertEquals(0,instance.getHp());
            assertTrue(instance.notValid());
        }
    }

    @RepeatedTest(10)
    @DisplayName("Test method forward")
    void forward() {
        int lastLocationY = instance.getLocationY();
        instance.forward();
        assertEquals(lastLocationY+instance.getSpeedY(),instance.getLocationY());
    }



}