package edu.hitsz.application.game;

import edu.hitsz.factory.MobEnemyFactory;

public class EasyGame extends Game {
    @Override
    protected void difficultyInitial() {
        bossScoreThreshold = Integer.MAX_VALUE;
        enemyMaxNumber = 5;
    }
    @Override
    protected void difficultyChangeAction() {
    }

}
