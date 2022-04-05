package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

/**
 * @author SoraShu
 */
public class BloodProp extends AbstractProp {

    public BloodProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void takeEffect(HeroAircraft heroAircraft) {
        heroAircraft.increaseHp(30);
    }
}
