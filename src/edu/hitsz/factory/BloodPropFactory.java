package edu.hitsz.factory;

import edu.hitsz.prop.AbstarctProp;
import edu.hitsz.prop.BloodProp;

public class BloodPropFactory implements PropFactory {

    @Override
    public AbstarctProp createProp(int locationX, int locationY, int speedX, int speedY) {
        return new BloodProp(locationX, locationY, speedX, speedY);
    }
}
