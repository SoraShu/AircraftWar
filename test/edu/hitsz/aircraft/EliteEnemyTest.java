package edu.hitsz.aircraft;

import edu.hitsz.factory.EliteEnemyFactory;
import edu.hitsz.prop.AbstractProp;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest {
    private EliteEnemy instance;
    private static EliteEnemyFactory factory;

    @BeforeAll
    static void create() {
        factory = new EliteEnemyFactory();
    }

    @BeforeEach
    void setUp() {
        instance = (EliteEnemy) factory.createEnemyAircraft(0,0,0,10,30);
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    @AfterAll
    static void tear() {
        factory = null;
    }



    @ParameterizedTest
    @DisplayName("Test method decreaseHp")
    @ValueSource(ints = {10,10,110})
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
    @DisplayName("Test leftProp")
    void leftProp() {
        List<AbstractProp> list = instance.leftProp();
        System.out.println(list);
        assertTrue(list.size()==1||list.isEmpty());
    }
}