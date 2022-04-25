package edu.hitsz.leaderboard.dao;

import edu.hitsz.leaderboard.Round;

import java.util.List;

public interface RoundDao {
    /*
     * 添加数据
     */
    void addRound(Round round);

    List<Round> getAllRounds();

    List<Round> getSortedRounds();

    Round getRound(int id);

    void delRound(int id);

}
