package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.factory.BloodPropFactory;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BloodPropTest {
    private BloodProp instance;
    private static BloodPropFactory factory;

    @BeforeAll
    static void setUpFactory() {
        factory = new BloodPropFactory();
    }

    @AfterAll
    static void tearDownFactory() {
        factory = null;
    }

    @BeforeEach
    void setUp() {
        instance = (BloodProp) factory.createProp(0, 0, 0, 10);
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    @Test
    @DisplayName("Test method notValid")
    void notValid() {
        instance.vanish();
        assertTrue(instance.notValid());
    }

    @Test
    @DisplayName("Test method takeEffet")
    void takeEffect() {
        HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();
        heroAircraft.decreaseHp(50);
        int currentHp = heroAircraft.getHp();
        instance.takeEffect(heroAircraft);
        assertEquals(currentHp+30,heroAircraft.getHp());
    }
}