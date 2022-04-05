package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;

/**
 * @author SoraShu
 */
public interface PropFactory {

    /**
     * 工厂方法
     *
     * @param locationX prop location<b>X</b>
     * @param locationY prop location<b>Y</b>
     * @param speedX    prop speed<b>X</b>
     * @param speedY    prop speed<b>Y</b>
     * @return prop
     */
    AbstractProp createProp(int locationX, int locationY, int speedX, int speedY);
}
