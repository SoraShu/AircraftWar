package edu.hitsz.aircraft;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.*;

import java.util.LinkedList;
import java.util.List;

public class EliteEnemy extends AbstractEnemyAircraft {

    private int shootNum = 1;
    private int power = 30;
    private int direction = 1;

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public List<AbstractBullet> shoot() {
        List<AbstractBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction * 2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction * 5;
        AbstractBullet abstractBullet;

        abstractBullet = new EnemyBullet(x, y, speedX, speedY, power);

        res.add(abstractBullet);
        return res;
    }

    @Override
    public List<AbstarctProp> LeftProp() {
        int temp = (int) (Math.random() * 8);
        List<AbstarctProp> list = new LinkedList<>();
        switch (temp) {
            case 5:
                list.add(new BloodProp(locationX, locationY, speedX, speedY / 5));
                break;
            case 6:
                list.add(new BombProp(locationX, locationY, speedX, speedY / 5));
                break;
            case 7:
                list.add(new BulletProp(locationX, locationY, speedX, speedY / 5));
                break;
            default:
                break;
        }
        return list;
    }
}
