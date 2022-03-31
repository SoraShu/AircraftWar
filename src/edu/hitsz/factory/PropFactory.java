package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;

public interface PropFactory {

    public AbstractProp createProp(int locationX, int locationY, int speedX, int speedY);
}
