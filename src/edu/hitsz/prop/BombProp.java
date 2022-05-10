package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.application.music.MusicManager;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.AbstractBullet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SoraShu
 */
public class BombProp extends AbstractProp {
    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
        enemyBullets = Main.game.getEnemyBullets();
        enemyAircrafts = Main.game.getEnemyAircrafts();
    }

    private final List<AbstractBullet> enemyBullets;
    private final List<AbstractEnemyAircraft> enemyAircrafts;

    private final List<AbstractFlyingObject> subscribers = new ArrayList<>();

    private void addSubscriber(AbstractFlyingObject subscriber) {
        subscribers.add(subscriber);
    }

    private void addSubscriberByList(List<? extends AbstractFlyingObject> subscribers) {
        subscribers.forEach(this::addSubscriber);
    }

    private void removeSubscriber(AbstractFlyingObject subscriber) {
        subscribers.remove(subscriber);
    }

    private void unsubscribeAll() {
        subscribers.clear();
    }

    private void notifyAllSubscribers() {
        subscribers.forEach(AbstractFlyingObject::onBombHandle);
    }

    @Override
    public void takeEffect(HeroAircraft heroAircraft) {
        System.out.println("BombSupply active!");
//        System.out.println(Main.game.getScore());
        MusicManager.start(MusicManager.MusicType.BOMB_EXPLOSION);
        addSubscriberByList(enemyAircrafts);
        addSubscriberByList(enemyBullets);
        notifyAllSubscribers();
        unsubscribeAll();
//        System.out.println(Main.game.getScore());
    }
}
