package edu.hitsz.aircraft;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.prop.AbstractProp;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractEnemyAircraft {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        score = 10;
    }

    @Override
    public List<AbstractBullet> shoot() {
        return new LinkedList<>();
    }

    @Override
    public List<AbstractProp> leftProp() {
        return new LinkedList<>();
    }
}
