package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.prop.AbstractProp;

import java.util.List;

/**
 * @author SoraShu
 */
public abstract class AbstractEnemyAircraft extends AbstractAircraft {

    protected int score;

    public AbstractEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    public int getScore() {
        return score;
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }
    }

    /**
     * 敌机死亡后生成道具
     *
     * @return List<AbstractProp>
     */
    public abstract List<AbstractProp> leftProp();
}