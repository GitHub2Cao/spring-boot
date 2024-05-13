package com.softnovo.base.generic.self;

class SelfBounded<T extends SelfBounded<T>> {}

class A extends SelfBounded<A> {}

class B extends SelfBounded<A> {}

class D extends SelfBounded {}


public class Test {
    public static void main(String[] args) {
        A a = new A();//a变量只能与A类型变量交互，这就是自限定的妙处
        SelfBounded<A> b =  new SelfBounded<>();

//        SelfBounded<Integer> bb = new SelfBounded<>();
    }
}
