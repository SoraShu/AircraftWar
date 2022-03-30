package edu.hitsz.factory;

import edu.hitsz.prop.AbstarctProp;

public interface PropFactory {

    AbstarctProp createProp(int locationX, int locationY, int speedX, int speedY);
}
