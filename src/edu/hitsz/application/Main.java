package edu.hitsz.application;

import edu.hitsz.application.gui.EndFrame;
import edu.hitsz.leaderboard.Round;
import edu.hitsz.leaderboard.dao.RoundDao;
import edu.hitsz.leaderboard.dao.RoundDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * 程序入口
 *
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    public static RoundDao roundDao;
    public static final Object lock = new Object();


    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello Aircraft War");
        String filePath;
        filePath = "./data.ser";

        try {
            roundDao = new RoundDaoImpl(filePath);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = new Game();
        frame.add(game);
        frame.setVisible(true);
        game.action();
        synchronized (lock) {
            lock.wait();
        }
        int roundScore = game.getScore();
        frame.remove(game);
        String playerName = JOptionPane.showInputDialog(
                frame,
                "游戏结束。你的得分是 " + roundScore + "。\n请输入名字记录得分:", "提示",
                JOptionPane.PLAIN_MESSAGE
        );
        if (Objects.equals(playerName, "")) {
            playerName = "Anonymous";
        }
        roundDao.addRound(new Round(playerName, roundScore));
        EndFrame endFrame = new EndFrame();
        frame.setContentPane(endFrame.getMainPanel());
        frame.setVisible(true);
    }
}
