package com.softnovo.algorithm.jvm;

import java.util.Objects;

public class MyObject {
    private boolean a;
    private long b;
    private char c;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyObject myObject = (MyObject) o;
        return a == myObject.a && b == myObject.b && c == myObject.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    public boolean isA() {
        return a;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public long getB() {
        return b;
    }

    public void setB(long b) {
        this.b = b;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }
}
