package edu.hitsz.leaderboard.dao;

import edu.hitsz.leaderboard.Round;

import java.util.List;

/**
 * @author SoraShu
 */
public interface RoundDao {

    /**
     * 增
     *
     * @param round 对局信息
     */
    void addRound(Round round);

    /**
     * 获取表
     *
     * @return 对局信息表
     */
    List<Round> getAllRounds();

    /**
     * 获取按分数降序的表
     *
     * @return 按分数降序的对局信息表
     */
    List<Round> getSortedRounds();

    /**
     * 查
     *
     * @param id hashid
     * @return 对应对局信息
     */
    Round getRound(int id);

    /**
     * 删
     *
     * @param id hashid
     */
    void delRound(int id);

    /**
     * 删
     *
     * @param round round object
     */
    void delRound(Round round);

}
