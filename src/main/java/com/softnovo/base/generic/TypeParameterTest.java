package com.softnovo.base.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TypeParameterTest {
    public static <T extends Comparable<T>> void mySort1(List<T> list) {
        Collections.sort(list);
    }

    //第二种声明：复杂，灵活性高
    public static <T extends Comparable<? super T>> void mySort2(List<T> list) {
        Collections.sort(list);
    }

    public static void main(String[] args) {
        //在这个方法中要创建一个 Animal List 和一个 Dog List，然后分别调用两个排序方法。
        List<Animal> animals = new ArrayList<>();

        animals.add(new Animal("25"));
        animals.add(new Dog("35"));


        List<Dog> dogs = new ArrayList<Dog>();
        dogs.add(new Dog("5"));
        dogs.add(new Dog("18"));

        mySort1(animals);
        mySort2(dogs);




    }
}
