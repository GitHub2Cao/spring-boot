package com.softnovo.base.generic.self;

class BasicHolder<T> {
    int No;
    T element;

    void set(T arg) {
        element = arg;
    }

    T get() {
        return element;
    }

    void f() {
        System.out.println("Subtype " + No);
    }
}

