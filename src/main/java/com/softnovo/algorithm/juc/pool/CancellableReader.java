package com.softnovo.algorithm.juc.pool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CancellableReader {
    private Thread readerThread; // only one at a time supported
    private FileInputStream dataFile;

    public synchronized void startReaderThread() throws IllegalStateException, FileNotFoundException {
        if (readerThread != null) throw new IllegalStateException();
        dataFile = new FileInputStream("data");
        new Thread(() -> doRead()).start();
    }

    protected synchronized void closeFile() { // utility method
        if (dataFile != null) {
            try {
                dataFile.close();
            } catch (IOException ignore) {
            }
            dataFile = null;
        }
    }

    protected void doRead() {
        try {
            while (!Thread.interrupted()) {
                try {
                    int c = dataFile.read();
                    if (c == -1) break;
                    else process(c);
                } catch (IOException ex) {
                    break; // perhaps first do other cleanup
                }
            }
        } finally {
            closeFile();
            synchronized (this) {
                readerThread = null;
            }
        }
    }

    private void process(int c) {
    }

    public synchronized void cancelReaderThread() {
        if (readerThread != null) readerThread.interrupt();
        closeFile();
    }
}
