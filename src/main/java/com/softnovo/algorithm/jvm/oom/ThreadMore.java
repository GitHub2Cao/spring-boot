package com.softnovo.algorithm.jvm.oom;

public class ThreadMore {
    public static void main(String[] args) {
        while(true){
            new Thread(new Runnable(){
                public void run() {
                    try {
                        Thread.sleep(10000000);
                    } catch(InterruptedException e) {

                    }
                }
            }).start();
        }
    }
}
