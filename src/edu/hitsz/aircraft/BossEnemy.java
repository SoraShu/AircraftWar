package edu.hitsz.aircraft;

import edu.hitsz.factory.BloodPropFactory;
import edu.hitsz.factory.BombPropFactory;
import edu.hitsz.factory.BulletPropFactory;
import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.strategy.ScatterShoot;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author SoraShu
 */
public class BossEnemy extends AbstractEnemyAircraft {

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        score = 100;
        shootNum = 3;
        power = 50;
        direction = 1;
        this.setShootStrategy(new ScatterShoot());
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
