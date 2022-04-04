package edu.hitsz.bullet;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EnemyBulletTest {
    private EnemyBullet instance;


    @AfterEach
    void tearDown() {
        instance = null;
    }

    @ParameterizedTest
    @DisplayName("Test method getPower")
    @ValueSource(ints = {10,20,30,40,50})
    void getPower(int num) {
        instance = new EnemyBullet(0,0,0,10,num);
        assertEquals(num,instance.getPower());
    }

    @RepeatedTest(10)
    @DisplayName("Test method forward")
    void forward() {
        instance = new EnemyBullet(0,0,10,10,10);
        int lastLocationY = instance.getLocationY();
        instance.forward();
        assertEquals(lastLocationY+instance.getSpeedY(),instance.getLocationY());
    }
}