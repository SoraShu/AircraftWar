package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.EliteEnemy;

/**
 * @author SoraShu
 */
public class EliteEnemyFactory extends EnemyFactory {
    public EliteEnemyFactory() {
        super();
        this.speedX = 5;
        this.speedY = 5;
        this.hp = 30;
    }

    @Override
    public AbstractEnemyAircraft createEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new EliteEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
