package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * @author SoraShu
 */
public abstract class EnemyFactory {


    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    protected int hp;
    protected int speedX;
    protected int speedY;

    public void changeHp(double ratio) {
        this.hp = (int) Math.ceil(hp * (1.0 + ratio));
    }

    public void changeSpeedX(double ratio) {
        this.speedX = (int) Math.ceil(speedX * (1.0 + ratio));
    }

    public void changeSpeedY(double ratio) {
        this.speedY = (int) Math.ceil(speedY * (1.0 + ratio));
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
