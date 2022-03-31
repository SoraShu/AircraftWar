package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;

public interface EnemyFactory {

    public AbstractEnemyAircraft createEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp);
}
