package edu.hitsz.leaderboard;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class RoundListSerializeTest {
    File f;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        f = null;
    }

    @Test
    void dser2() throws IOException, ClassNotFoundException {

        var f2 = new File("./data.ser");
        var drounds = RoundListSerialize.dser(f2);
        for(var dround:drounds) {
            System.out.println(dround.toString());
        }
    }

    @Test
    void dser() throws IOException, ClassNotFoundException {
        f = new File("./test/data.ser");
        List<Round> rounds = new ArrayList<>();
        rounds.add(new Round("TestA",100));
        rounds.add(new Round("TestB",200));
        rounds.add(new Round("TestC",300));
        RoundListSerialize.ser(f, rounds);
        var drounds = RoundListSerialize.dser(f);
        for(var dround:drounds) {
            System.out.println(dround.toString());
        }

    }
}