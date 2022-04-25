package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.AbstractBullet;

import java.util.List;

/**
 * @author SoraShu
 */
public interface Shoot {
    /**
     * 工厂方法
     *
     * @param aircraft 传入调用者
     * @return 子弹列表: list
     */
    List<AbstractBullet> doShootAction(AbstractAircraft aircraft);
}
