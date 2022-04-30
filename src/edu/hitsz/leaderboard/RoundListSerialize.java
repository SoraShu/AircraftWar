package edu.hitsz.leaderboard;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SoraShu
 */
public class RoundListSerialize {
    public static void ser(File file, List<Round> rounds) throws IOException {
        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        Round[] roundArray = rounds.toArray(new Round[0]);
        oos.writeObject(roundArray);
        oos.close();
        os.close();
    }

    public static List<Round> dser(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        var listOfRounds = List.of((Round[]) ois.readObject());
        ois.close();
        fis.close();
        return new ArrayList<>(listOfRounds);
    }
}
