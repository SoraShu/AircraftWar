package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.BossEnemy;

/**
 * @author SoraShu
 */
public class BossEnemyFactory extends EnemyFactory {
    public BossEnemyFactory() {
        super();
        this.speedX = 10;
        this.speedY = 0;
        this.hp = 500;
    }

    @Override
    public AbstractEnemyAircraft createEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new BossEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
