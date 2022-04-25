package edu.hitsz.factory;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;

/**
 * @author SoraShu
 */
public class EnemyBulletFactory implements BulletFactory {

    @Override
    public AbstractBullet createBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        return new EnemyBullet(locationX, locationY, speedX, speedY, power);
    }
}
