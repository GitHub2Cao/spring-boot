package com.softnovo.algorithm.exception;

public class TestWhySlaw {
    public static class LowLevelException extends Exception {
        public LowLevelException() { super(); }
        public LowLevelException(String msg, Throwable cause) { super(msg, cause); }
        public LowLevelException(String msg) { super(msg); }
        public LowLevelException(Throwable cause) { super(cause); }
    }

    public static class MidLevelException extends Exception {
        public MidLevelException(String s, LowLevelException e) {
            super(s, e);
        }
        //...与LowLevelException实现类似，省略代码实现...
    }

    public static class HighLevelException extends RuntimeException {
        public HighLevelException(String s, MidLevelException e) {
            super(s, e);
        }
        //...与LowLevelException实现类似，省略代码实现...
    }

    public static void fa() throws LowLevelException {
        throw new LowLevelException("LowLevelException-msg");
    }

    public static void fb() throws LowLevelException {
        fa();
    }

    public static void fc() throws MidLevelException {
        try {
            fb();
        } catch (LowLevelException e) {
            throw new MidLevelException("MidLevelException-msg", e);
        }
    }

    public static void fd() {
        try {
            fc();
        } catch (MidLevelException e) {
            throw new HighLevelException("HighLevelException-msg", e);
        }
    }

    public static void fe() {
        fd();
    }

    public static void main(String[] args) {
        try {
            fe();
        } catch(HighLevelException e) {
            e.printStackTrace();
        }
    }
}
