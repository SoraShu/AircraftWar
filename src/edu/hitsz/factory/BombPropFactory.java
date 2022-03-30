package edu.hitsz.factory;

import edu.hitsz.prop.AbstarctProp;
import edu.hitsz.prop.BombProp;

public class BombPropFactory implements PropFactory {
    @Override
    public AbstarctProp createProp(int locationX, int locationY, int speedX, int speedY) {
        return new BombProp(locationX, locationY, speedX, speedY);
    }
}
