package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.MobEnemy;

/**
 * @author SoraShu
 */
public class MobEnemyFactory extends EnemyFactory {
    public MobEnemyFactory() {
        super();
        this.speedX = 0;
        this.speedY = 10;
        this.hp = 30;
    }

    @Override
    public AbstractEnemyAircraft createEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new MobEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
