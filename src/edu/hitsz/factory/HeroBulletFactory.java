package edu.hitsz.factory;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.HeroBullet;

/**
 * @author SoraShu
 */
public class HeroBulletFactory implements BulletFactory {

    @Override
    public AbstractBullet createBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        return new HeroBullet(locationX, locationY, speedX, speedY, power);
    }
}
