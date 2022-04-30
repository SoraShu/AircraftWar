package edu.hitsz.leaderboard.dao;

import edu.hitsz.leaderboard.Round;
import edu.hitsz.leaderboard.RoundListSerialize;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author SoraShu
 */
public class RoundDaoImpl implements RoundDao {

    private File file;
    private List<Round> rounds;


    public RoundDaoImpl(File file) throws IOException, ClassNotFoundException {
        this.file = file;
        try {
            rounds = RoundListSerialize.dser(this.file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Create empty list!");
            rounds = new ArrayList<>();
        }
//        rounds = RoundListSerialize.dser(this.file);
    }

    public RoundDaoImpl(String filePath) throws IOException, ClassNotFoundException {
        file = new File(filePath);
        try {
            rounds = RoundListSerialize.dser(this.file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Create empty list!");
            rounds = new ArrayList<>();
        }
    }

    public void writeToFile() throws IOException {
        RoundListSerialize.ser(this.file, this.rounds);
    }

    @Override
    public void addRound(Round round) {
        rounds.add(round);
        try {
            writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void addRound(Round round, boolean overWrite) throws IOException {
        addRound(round);
        if (overWrite) {
            writeToFile();
        }
    }

    @Override
    public List<Round> getAllRounds() {
        return new ArrayList<>(rounds);
    }

    @Override
    public List<Round> getSortedRounds() {
        List<Round> sortedRounds = new ArrayList<>(rounds);
        sortedRounds.sort(Comparator.comparing(Round::getFinalScore).reversed());
        return sortedRounds;
    }

    @Override
    public Round getRound(int id) {
        for (Round round : this.rounds) {
            if (round.getId() == id) {
                return round;
            }
        }
        System.err.println("Round not found!");
        return null;
    }

    @Override
    public void delRound(int id) {
        rounds.removeIf(s -> s.getId() == id);
        try {
            writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delRound(Round round) {
        rounds.remove(round);
        try {
            writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
