package edu.hitsz.application.game;

public class DifficultGame extends Game {
    @Override
    protected void difficultyInitial() {
        bossScoreThreshold = 300;
        enemyMaxNumber = 5;
        enemyShootDuration = 600;
    }
    @Override
    protected void difficultyChangeAction() {
        System.out.println("diff up!");
        mobEnemyFactory.changeHp(0.01);
        eliteEnemyFactory.changeHp(0.1);
        enemyMaxNumber += 1;
        if (createDuration >= 100) {
            createDuration = (int) (createDuration / 1.1);
        }
        if (enemyShootDuration > 100) {
            enemyShootDuration = (int) (enemyShootDuration / 1.05);
        }
    }


}
