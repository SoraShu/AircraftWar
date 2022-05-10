package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * @author SoraShu
 */
public abstract class EnemyFactory {


    protected int hp;
    protected int speedX;
    protected int speedY;

    public void changeHp(int deltaHp) {
        this.hp += deltaHp;
    }

    public void changeSpeedX(int deltaSpeedX) {
        this.speedX = deltaSpeedX;
    }

    public void changeSpeedY(int deltaSpeedY) {
        this.speedY = deltaSpeedY;
    }

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
    public abstract AbstractEnemyAircraft createEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp);

    public AbstractEnemyAircraft createEnemyAircraft() {
        return createEnemyAircraft(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2),
                speedX,
                speedY,
                hp
        );
    }
}
