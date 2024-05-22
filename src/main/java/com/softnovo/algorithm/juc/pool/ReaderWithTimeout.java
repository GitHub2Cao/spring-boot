package com.softnovo.algorithm.juc.pool;

import java.io.IOException;
import java.io.InputStream;

public class ReaderWithTimeout {
    // …
    void attemptRead(InputStream stream, long timeout) throws Exception {
        long startTime = System.currentTimeMillis();
        try {
            for (;;) {
                if (stream.available() > 0) {
                    int c = stream.read();
                    if (c != -1) process(c);
                    else break; // eof
                } else {
                    try {
                        Thread.sleep(100); // arbitrary fixed back-off time
                    } catch (InterruptedException ie) {
                        /* … quietly wrap up and return … */
                    }
                    long now = System.currentTimeMillis();
                    if (now - startTime >= timeout) {
                        /* … fail …*/
                    }
                }
            }
        } catch (IOException ex) { /* … fail … */ }
    }

    private void process(int c) {

    }
}
