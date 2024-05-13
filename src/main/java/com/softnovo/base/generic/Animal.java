package com.softnovo.base.generic;

public class Animal implements Comparable<Animal> {
    public String name;

    public Animal() {}

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Animal o) {
        return 0;
    }

//    @Override
//    public int compareTo(Animal o) {
//        return 0;
//    }
}
