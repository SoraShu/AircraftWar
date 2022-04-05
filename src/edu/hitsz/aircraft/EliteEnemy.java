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
public class EliteEnemy extends AbstractEnemyAircraft {

    private int shootNum = 1;
    private int power = 30;
    private int direction = 1;

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
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
        Random rnd = new Random();
        int temp = rnd.nextInt(8);
        List<AbstractProp> list = new LinkedList<>();
        PropFactory propfactory = null;
        switch (temp) {
            case 5:
                propfactory = new BloodPropFactory();
                break;
            case 6:
                propfactory = new BombPropFactory();
                break;
            case 7:
                propfactory = new BulletPropFactory();
                break;
            default:
                break;
        }
        if (propfactory != null) {
            list.add(propfactory.createProp(locationX, locationY, speedX, speedY));
        }
        return list;
    }
}
