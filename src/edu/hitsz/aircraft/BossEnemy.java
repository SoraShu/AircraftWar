package edu.hitsz.aircraft;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.factory.BloodPropFactory;
import edu.hitsz.factory.BombPropFactory;
import edu.hitsz.factory.BulletPropFactory;
import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.AbstractProp;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author SoraShu
 */
public class BossEnemy extends AbstractEnemyAircraft {

    private int shootNum = 3;

    private int power = 50;

    private int direction = 1;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public List<AbstractBullet> shoot() {
        List<AbstractBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction * 2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction * 5;
        AbstractBullet abstractBullet;

        abstractBullet = new EnemyBullet(x, y, speedX, speedY, power);

        res.add(abstractBullet);
        return res;
    }

    @Override
    public List<AbstractProp> leftProp() {
        List<AbstractProp> list = new LinkedList<>();
        PropFactory[] propfactoryset = new PropFactory[3];
        propfactoryset[0] = new BloodPropFactory();
        propfactoryset[1] = new BombPropFactory();
        propfactoryset[2] = new BulletPropFactory();
        for (PropFactory propfactory : propfactoryset) {
            Random rnd = new Random();
            int propSpeedX = rnd.nextInt(10);
            list.add(propfactory.createProp(locationX, locationY, propSpeedX, 10));
        }

        return list;
    }
}
