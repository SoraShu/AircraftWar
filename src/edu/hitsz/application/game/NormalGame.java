package edu.hitsz.application.game;

public class NormalGame extends Game {
    @Override
    protected void difficultyInitial() {
        bossScoreThreshold = 400;
        enemyMaxNumber = 5;
    }
    @Override
    protected void difficultyChangeAction() {
        System.out.println("diff up!");
        eliteEnemyFactory.changeHp(0.05);
    }

}
