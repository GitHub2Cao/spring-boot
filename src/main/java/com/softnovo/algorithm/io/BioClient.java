package com.softnovo.algorithm.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BioClient {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Socket socket = new Socket("127.0.0.1", 1192);
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(("hello").getBytes());

                    Thread.sleep(1000);

                    InputStream inputStream = socket.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = inputStream.read(buffer);
                    System.out.println(Thread.currentThread().getName() + " _ " + new String(buffer, 0, len));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
