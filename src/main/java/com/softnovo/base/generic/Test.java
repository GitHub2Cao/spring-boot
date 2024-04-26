package com.softnovo.base.generic;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Animal> animalList = Lists.newArrayList(new Animal("animal1"), new Animal("animal2"), new Animal("animal3"));

        List<Dog> dogList = Lists.newArrayList(new Dog("dog1"), new Dog("dog2"), new Dog("dog3"));

        List<MiniDog> miniDogList = Lists.newArrayList(new MiniDog("miniDog1"), new MiniDog("miniDog2"), new MiniDog("miniDog3"));

//        show(dogList);
//        show(miniDogList);
        System.out.println("-------------");
        dogList.addAll(miniDogList);
        //show(dogList);

        showAnimal(dogList);

        List<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);

        List<Number> nums = new ArrayList<>(); // now this line works
        /*
         *without using ? syntax above line did not use to compile earlier
         */
        List<Double> doubleList = new ArrayList<Double>();
        doubleList.add(1.0);
        nums.addAll(doubleList); // compile time error


        List<? extends Animal> animals = new ArrayList<>(Lists.newArrayList(new Animal(), new Dog(), new MiniDog()));


        //Dog dog = new Dog("dog1");


        //Dog dog = new MiniDog();
        List<? extends Dog> flist = new ArrayList<MiniDog>();

        // 当A≤B时，有f(B)≤f(A)成立；
        Dog dog = new MiniDog();
        List<? super Dog> dlist = new ArrayList<Dog>();


//        List<Integer> src = Arrays.asList(1, 2, 3, 4, 5);
//        List<String> dst = new ArrayList<>();
//        copy2(src, dst);
//        System.out.println(dst);
//
//        List<Number> ret1 = map2(src, new Mapper<Integer, Number>() {
//            @Override
//            public Number map(Integer integer) {
//                return integer * 1.2;
//            }
//        });
//
//        System.out.println(ret1);
//
//        print(new int[] {1, 2222, 3, 4, 5});
//        print(1, 2222, 3, 4, 5);
//        print();
    }

    private static void show(List<? extends Dog> dogList) {
        for (Dog dog : dogList) {
            System.out.println(dog.getClass().getName());
            System.out.println(dog.getName());
        }
    }

    private static void showAnimal(List<? super Dog> dogList) {
        dogList.add(new Dog("dogggg"));
        dogList.add(new MiniDog("mininininin"));
        for (Object o : dogList) {
            if (o instanceof Dog) {
                System.out.println(((Dog) o).getName());
            }
        }

    }

    public static <E> void print(E ... e) {
        System.out.println(Arrays.toString(e));
    }

    private static interface Mapper<T, R> {
        R map(T t);
    }

    private static <T, R> List<R> map2(List<T> list, Mapper<T, R> mapper) {
        List<R> l = new ArrayList<>();
        for (T t : list) {
            R r = mapper.map(t);
            l.add(r);
        }
        return l;
    }

    private static List<Number> map1(List<Integer> list, Mapper<Integer, Number> mapper) {
        List<Number> result = new ArrayList<>();
        for (Integer item : list) {
            Number r = mapper.map(item);
            result.add(r);
        }
        return result;
    }

    // 只能搞定List<Integer>拷贝给List<Number>，无法处理其他类型。
    private static void copy1(List<Integer> src, List<Number> dst) {
        for (Integer obj : src) {
            dst.add(obj);
        }
    }

    // 但没有编译期约束，运行不安全（比如dst是List<String>类型）
    private static <T1, T2> void copy2(List<T1> src, List<T2> dst) {
        for (T1 obj : src) {
            T2 obj1 = (T2) obj;
            dst.add(obj1);
        }
    }

    /**
     * extends 读，super 写
     *
     * @param src src里必须是T的子类：List<? extends T>
     * @param dst dst里必须是T的父类：List<? super T>
     * @param <T>
     */
    private static <T> void copy3(List<? extends T> src, List<? super T> dst) {
        for (T obj : src) {
            dst.add(obj); // 也不用强制转化.
        }
    }

}
