package edu.hitsz.leaderboard;

import java.io.Serializable;

public class Round implements Serializable {
    private static final long serialVersionUID = 1145141919810L;

    public int getId() {
        return id;
    }

    public String getPlayTime() {
        return playTime;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getFinalScore() {
        return finalScore;
    }

    private int id;
    private String playTime;
    private String playerName;
    private int finalScore;

    public Round(String playername, int finalScore) {
        this.id = System.identityHashCode(this);
        this.playTime = new java.util.Date().toString();
        this.playerName = playername;
        this.finalScore = finalScore;
    }

    public String toString() {
        return this.playerName + " " + String.format("%4d",this.finalScore) + " " + this.playTime;
    }

}
