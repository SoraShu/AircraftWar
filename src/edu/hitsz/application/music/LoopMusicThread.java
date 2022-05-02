package edu.hitsz.application.music;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class LoopMusicThread extends MusicThread {
    public LoopMusicThread(String filePath) {
        super(filePath);
    }
    @Override
    public void run() {
        InputStream stream;
        //noinspection InfiniteLoopStatement
        do {
            stream = new ByteArrayInputStream(samples);
            play(stream);
        } while (true);
    }
}
