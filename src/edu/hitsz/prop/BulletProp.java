package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.ScatterShoot;

/**
 * @author SoraShu
 */
public class BulletProp extends AbstractProp {
    public BulletProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void takeEffect(HeroAircraft heroAircraft) {
        System.out.println("BulletSupply active!");
        heroAircraft.setShootNum(3);
        heroAircraft.setShootStrategy(new ScatterShoot());
        // TODO: BulletProp need to be perf
    }
}
