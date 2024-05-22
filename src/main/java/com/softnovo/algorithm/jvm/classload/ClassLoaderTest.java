package com.softnovo.algorithm.jvm.classload;

public class ClassLoaderTest {
    //默认父类加载器为AppClassLoader
    public static class ClassLoaderA extends ClassLoader {
    }

    //通过构造函数指定父类加载器
    public static class ClassLoaderB extends ClassLoader {
        public ClassLoaderB(ClassLoader parent) {
            super(parent);
        }
    }

    public static class ClassLoaderC extends ClassLoaderA {

    }

    public static void main(String[] args) {
        ClassLoaderC classLoaderC = new ClassLoaderC();
        System.out.println(classLoaderC.getParent());
//        ClassLoaderB loader = new ClassLoaderB(new ClassLoaderA());

//        System.out.println(loader);
//
//        System.out.println(loader.getParent());
//
//        System.out.println(loader.getParent().getParent());
//
//        System.out.println(loader.getParent().getParent().getParent());
//
//        System.out.println(loader.getParent().getParent().getParent().getParent());
    }


}
