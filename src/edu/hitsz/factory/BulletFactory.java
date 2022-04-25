package edu.hitsz.factory;

import edu.hitsz.bullet.AbstractBullet;

/**
 * @author SoraShu
 */
public interface BulletFactory {
    /**
     * 工厂方法
     *
     * @param locationX bullet location<b>X</b>
     * @param locationY bullet locationy<b>Y</b>
     * @param speedX    bullet speed<b>X</b>
     * @param speedY    bullet speed<b>Y</b>
     * @param power     bullet power
     * @return 子弹: AbstractBullet
     */
    AbstractBullet createBullet(int locationX, int locationY, int speedX, int speedY, int power);
}
