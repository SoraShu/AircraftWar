package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;

/**
 * @author SoraShu
 */
public interface EnemyFactory {

    /**
     * 工厂方法
     *
     * @param locationX enemy location<b>X</b>
     * @param locationY enemy locationy<b>Y</b>
     * @param speedX    enemy speed<b>X</b>
     * @param speedY    enemy speed<b>Y</b>
     * @param hp        enemy maxHp
     * @return enemyaircraft
     */
    AbstractEnemyAircraft createEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp);
}
