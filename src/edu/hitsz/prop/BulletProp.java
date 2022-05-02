package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.music.MusicManager;
import edu.hitsz.strategy.ScatterShoot;
import edu.hitsz.strategy.StraightShoot;

import java.util.concurrent.TimeUnit;

/**
 * @author SoraShu
 */
public class BulletProp extends AbstractProp {
    public static long getId() {
        return id;
    }

    private static long id;

    public BulletProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void takeEffect(HeroAircraft heroAircraft) {
        System.out.println("BulletSupply active!");
        MusicManager.start(MusicManager.MusicType.GET_SUPPLY);
        BulletProp.id = System.identityHashCode(this);
        Thread t = new Thread(() ->
        {
            heroAircraft.setShootStrategy(new ScatterShoot());
            heroAircraft.setShootNum(3);
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException ignored) {
            }
            // 道具时间不叠加，检测 id 是否被更改，若未更改则可以失效
            if (BulletProp.id == System.identityHashCode(this)) {
                heroAircraft.setShootNum(1);
                heroAircraft.setShootStrategy(new StraightShoot());
            }
        }
        );
        t.start();
    }
}
