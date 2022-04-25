package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.factory.BulletFactory;
import edu.hitsz.factory.EnemyBulletFactory;
import edu.hitsz.factory.HeroBulletFactory;

import java.util.LinkedList;
import java.util.List;

public class StraightShoot implements Shoot {
    @Override
    public List<AbstractBullet> doShootAction(AbstractAircraft aircraft) {
        List<AbstractBullet> res = new LinkedList<>();
        AbstractBullet abstractBullet;
        BulletFactory bulletFactory;
        int shootNum = aircraft.getShootNum();

        switch (aircraft.getDirection()) {
            case -1:
                bulletFactory = new HeroBulletFactory();
                break;
            case 1:
                bulletFactory = new EnemyBulletFactory();
                break;
            default:
                throw new IllegalArgumentException("BulletFactory uninitialized !");
        }
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            abstractBullet = bulletFactory.createBullet(
                    aircraft.getLocationX() + (i * 2 - shootNum + 1) * 10,
                    aircraft.getLocationY(),
                    0,
                    aircraft.getSpeedY() + aircraft.getDirection() * 10,
                    aircraft.getPower());
            res.add(abstractBullet);
        }

        return res;
    }
}
