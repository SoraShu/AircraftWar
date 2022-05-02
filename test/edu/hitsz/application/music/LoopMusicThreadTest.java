package edu.hitsz.application.music;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class LoopMusicThreadTest {
    Thread s;

    @BeforeEach
    void setUp() {
        s = new LoopMusicThread("./src/videos/bullet.wav");
    }


    @Test
    @Timeout(value = 17000,unit = TimeUnit.MILLISECONDS)
    void run() throws InterruptedException {
        s.start();
        Thread.sleep(5000);
        s.interrupt();
        Thread.sleep(10000);
    }
}