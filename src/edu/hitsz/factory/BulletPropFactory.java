package edu.hitsz.factory;

import edu.hitsz.prop.AbstarctProp;
import edu.hitsz.prop.BulletProp;

public class BulletPropFactory implements PropFactory {
    @Override
    public AbstarctProp createProp(int locationX, int locationY, int speedX, int speedY) {
        return new BulletProp(locationX, locationY, speedX, speedY);
    }
}
