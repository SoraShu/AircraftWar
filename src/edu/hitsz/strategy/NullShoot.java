package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.AbstractBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * @author SoraShu
 */
public class NullShoot implements Shoot {

    @Override
    public List<AbstractBullet> doShootAction(AbstractAircraft aircraft) {
        return new LinkedList<>();
    }
}
