package com.softnovo.algorithm.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BioEchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress("127.0.0.1", 1192));
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("get client connected and request");
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            byte[] data = new byte[1024];
            while (true) { //持续接收客户端发来的数据
                try {
                    // read()为阻塞函数，直到读取到数据再返回
                    socket.getInputStream().read(data);
                    // write()为阻塞函数，全部写完成才会返回
                    socket.getOutputStream().write(data); //echo
                } catch (IOException e) {
                    // log and exit
                    break;
                }
            }
        }
    }
}
