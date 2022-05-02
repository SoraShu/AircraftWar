package edu.hitsz.application;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.music.MusicManager;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.factory.BossEnemyFactory;
import edu.hitsz.factory.EliteEnemyFactory;
import edu.hitsz.factory.EnemyFactory;
import edu.hitsz.factory.MobEnemyFactory;
import edu.hitsz.leaderboard.Round;
import edu.hitsz.prop.AbstractProp;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    private final List<AbstractEnemyAircraft> enemyAircrafts;
    private final List<AbstractEnemyAircraft> bossAircrafts;
    private final List<AbstractBullet> heroBullets;
    private final List<AbstractBullet> enemyBullets;
    private final List<AbstractProp> allProps;

    private final EnemyFactory mobEnemyFactory = new MobEnemyFactory();
    private final EnemyFactory eliteEnemyFactory = new EliteEnemyFactory();
    private final EnemyFactory bossEnemyFactory = new BossEnemyFactory();
    private int enemyMaxNumber = 5;
    private int bossScoreThreshold = 400;

    private boolean gameOverFlag = false;

    public int getScore() {
        return score;
    }

    private int score = 0;
    private int time = 0;
    private int bossCounter = 0;
    private String FilePath = "./data.ser";
    private Round thisRound;
    private List<Round> rounds;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;


    public Game() {
        heroAircraft = HeroAircraft.getHeroAircraft();

        enemyAircrafts = new LinkedList<>();
        bossAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        allProps = new LinkedList<>();

        /*
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {
        MusicManager.startBgm();

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生
                if (enemyAircrafts.size() < enemyMaxNumber) {
                    Random rnd = new Random();
                    int ran = rnd.nextInt(5);
                    if (ran <= 3) {
                        enemyAircrafts.add(mobEnemyFactory.createEnemyAircraft(
                                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2),
                                0,
                                10,
                                30
                        ));
                    } else {
                        //System.out.println("elite");
                        enemyAircrafts.add(eliteEnemyFactory.createEnemyAircraft(
                                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2),
                                5,
                                5,
                                30
                        ));
                    }
                }
                if (bossCounter > 0 && bossAircrafts.size() == 0) {
                    bossAircrafts.add(bossEnemyFactory.createEnemyAircraft(
                            (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                            (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2),
                            10,
                            0,
                            200
                    ));
                    bossCounter--;
                }
                // 飞机射出子弹
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 道具移动
            propsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");

//                printLeaderboard();
                synchronized (Main.LOCK) {
                    Main.LOCK.notify();
                }
                MusicManager.start(MusicManager.MusicType.GAME_OVER);
                MusicManager.interruptAll();
            }

        };

        /*
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        //  敌机射击
        for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
            enemyBullets.addAll(enemyAircraft.shoot());
        }
        // boss射击
        for (AbstractEnemyAircraft bossAircraft : bossAircrafts) {
            enemyBullets.addAll(bossAircraft.shoot());
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
        // 英雄射击音效
        MusicManager.start(MusicManager.MusicType.SHOOT);
    }

    private void bulletsMoveAction() {
        for (AbstractBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (AbstractBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
        for (AbstractEnemyAircraft bossAircraft : bossAircrafts) {
            bossAircraft.forward();
        }
    }

    private void propsMoveAction() {
        for (AbstractProp prop : allProps) {
            prop.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // 敌机子弹攻击英雄
        for (AbstractBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if (heroAircraft.notValid()) {
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }

        // 英雄子弹攻击敌机
        for (AbstractBullet bullet : heroBullets) {
            // 子弹攻击 Mob or Elite
            if (shootEnemy(bullet, enemyAircrafts)) {
                continue;
            }
            // 子弹攻击 Boss
            shootEnemy(bullet, bossAircrafts);
        }

        // 我方获得道具，道具生效
        for (AbstractProp prop : allProps) {
            if (prop.notValid()) {
                continue;
            }
            if (heroAircraft.notValid()) {
                continue;
            }
            if (heroAircraft.crash(prop)) {
                prop.takeEffect(heroAircraft);
                prop.vanish();
            }
        }

    }

    /**
     * 子弹射向敌机
     *
     * @param bullet         子弹
     * @param enemyAircrafts 敌机表
     * @return 跳过循环？
     */
    private boolean shootEnemy(AbstractBullet bullet, List<AbstractEnemyAircraft> enemyAircrafts) {
        if (bullet.notValid()) {
            return true;
        }

        for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
            int preScore = score;
            if (enemyAircraft.notValid()) {
                continue;
            }
            if (enemyAircraft.crash(bullet)) {
                enemyAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
                if (enemyAircraft.notValid()) {
                    score += enemyAircraft.getScore();
                    allProps.addAll(enemyAircraft.leftProp());
                }
            }
            // 英雄机 与 敌机相撞
            if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                enemyAircraft.vanish();
                heroAircraft.decreaseHp(Integer.MAX_VALUE);
            }
            if (preScore / bossScoreThreshold < score / bossScoreThreshold) {
                bossCounter++;
                System.out.println("count:" + bossCounter);
            }
        }
        return false;
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        bossAircrafts.removeIf(AbstractFlyingObject::notValid);
        allProps.removeIf(AbstractFlyingObject::notValid);
    }


    private void printLeaderboard() {
        System.out.println("********************************************************");
        System.out.println("*                     leaderboard                      *");
        System.out.println("********************************************************");
//        try {
//            roundDao = new RoundDaoImpl(FilePath);
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        thisRound = new Round("testUserName", score);
        Main.roundDao.addRound(thisRound);
        rounds = Main.roundDao.getSortedRounds();
        int i = 1;
        for (Round round : rounds) {
            System.out.println("第 " + String.format("%2d", i) + " 名: " + round.toString());
            i += 1;
        }
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param g:Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g, bossAircrafts);

        paintImageWithPositionRevised(g, allProps);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }


}
