package edu.hitsz.aircraft;

import edu.hitsz.factory.BloodPropFactory;
import edu.hitsz.factory.BombPropFactory;
import edu.hitsz.factory.BulletPropFactory;
import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.strategy.StraightShoot;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author SoraShu
 */
public class EliteEnemy extends AbstractEnemyAircraft {

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        score = 20;
        shootNum = 1;
        power = 30;
        direction = 1;
        this.setShootStrategy(new StraightShoot());
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
