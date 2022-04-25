package edu.hitsz.leaderboard.dao;

import edu.hitsz.leaderboard.Round;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RoundDaoImplTest {
    File f = new File("./test/data2.ser");

//    @BeforeEach
//    void setUp() {
////        f = new File("./test/data2.ser");
//
//    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void getSortedRounds() throws IOException, ClassNotFoundException {
        RoundDaoImpl dao = new RoundDaoImpl(f);
        dao.addRound(new Round("A",1));
        dao.addRound(new Round("B",3));
        dao.addRound(new Round("C",2));
        var sortedlist = dao.getSortedRounds();
        var list = dao.getAllRounds();
        var sortedlist2 = dao.getSortedRounds();
        System.out.println(sortedlist.toString());
        System.out.println(list.toString());
        System.out.println(sortedlist==sortedlist2);
        System.out.println(sortedlist.get(0)==sortedlist2.get(0));

    }
}