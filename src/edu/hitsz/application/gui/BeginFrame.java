package edu.hitsz.application.gui;

import edu.hitsz.application.Difficulty;
import edu.hitsz.application.Main;
import edu.hitsz.application.music.MusicManager;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SoraShu
 */
public class BeginFrame {
    private JPanel mainPanel;
    private JButton easyButton;
    private JButton nomalButton;
    private JButton difficultButton;
    private JCheckBox soundCheckBox;
    private JLabel nameLabel;
    private JLabel soundLabel;

    private final Map<Boolean, String> soundMap = new HashMap<>() {
        {
            put(true, "On");
            put(false, "Off");
        }
    };

    public BeginFrame() {
        setSound();


        easyButton.addActionListener(e -> {
            Main.setDifficulty(Difficulty.EASY);
            synchronized (Main.LOCK) {
                Main.LOCK.notify();
            }
        });
        nomalButton.addActionListener(e -> {
            Main.setDifficulty(Difficulty.NORMAL);
            synchronized (Main.LOCK) {
                Main.LOCK.notify();
            }
        });
        difficultButton.addActionListener(e -> {
            Main.setDifficulty(Difficulty.DIFFICULT);
            synchronized (Main.LOCK) {
                Main.LOCK.notify();
            }
        });
        soundCheckBox.addActionListener(e -> setSound());
    }

    private void setSound() {
        soundCheckBox.setText(soundMap.get(soundCheckBox.isSelected()));
        MusicManager.setIsPlaySound(soundCheckBox.isSelected());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
