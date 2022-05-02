package edu.hitsz.application.music;

/**
 * @author SoraShu
 */
public class MusicManager {
    public enum MusicType {
        /**
         * BGM
         */
        BGM("bgm.wav"),
        /**
         * BOSS 出场 BGM
         */
        BOSS_BGM("bgm_boss.wav"),
        /**
         * 炸弹爆炸
         */
        BOMB_EXPLOSION("bomb_explosion.wav"),
        /**
         * 发射子弹
         */
        SHOOT("bullet.wav"),
        /**
         * 子弹击中
         */
        HIT("bullet_hit.wav"),
        /**
         * 获得道具
         */
        GET_SUPPLY("get_supply.wav"),
        /**
         * 游戏结束
         */
        GAME_OVER("game_over.wav");
        private final String filename;

        MusicType(String filename) {
            this.filename = filename;
        }

        public String getFilePath() {
            return "./src/videos/" + filename;
        }
    }

    public static void setIsPlaySound(boolean isPlaySound) {
        MusicManager.isPlaySound = isPlaySound;
    }

    // 是否播放发射子弹声音
    private static final boolean isPlayBulletSound = false;
    private static boolean isPlaySound;
    private static Thread BossBgm;
    private static Thread Bgm;


    public static void start(MusicType type) {
        if (!isPlaySound) {
            return;
        }
        switch (type) {
            case BOMB_EXPLOSION:
            case HIT:
            case GET_SUPPLY:
            case GAME_OVER:
                new MusicThread(type.getFilePath()).start();
                break;
            case SHOOT:
                if (isPlayBulletSound) {
                    new MusicThread(type.getFilePath()).start();
                }
                break;
            case BGM:
                Bgm = new LoopMusicThread(type.getFilePath());
                Bgm.start();
                break;
            case BOSS_BGM:
                BossBgm = new LoopMusicThread(type.getFilePath());
                BossBgm.start();
                break;
            default:
                throw new IllegalArgumentException("MusicType");
        }
    }

    public static void startBossBgm() {
        start(MusicType.BOSS_BGM);
    }

    public static void startBgm() {
        start(MusicType.BGM);
    }

    public static void interruptAll() {
        Bgm.interrupt();
        BossBgm.interrupt();
    }

    public static void interruptBossBgm() {
        BossBgm.interrupt();
    }

    public static void interruptBgm() {
        Bgm.interrupt();
    }
}
