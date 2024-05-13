package com.softnovo.algorithm.lambda.employees;

import org.openjdk.jol.info.ClassLayout;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestEmployee {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Sales", 6000),
                new Employee("Bob", "Sales", 5000),
                new Employee("Charlie", "HR", 5500),
                new Employee("David", "HR", 6500),
                new Employee("Ella", "IT", 7500),
                new Employee("Frank", "IT", 7000),
                new Employee("Grace", "HR", 4500)
        );

//        Integer a = 10;
//        f(a);
//        System.out.println(a);
        // 保留薪水大于5000的员工
//        List<Employee> collect = employees.stream().filter(employee -> employee.getSalary() > 5000).collect(Collectors.toList());
//        System.out.println(collect.size() + " == " + collect);


//
//        int i = 100;
//        int j = 100;
//        compare(i, j);
//
//        int x = 12; //十进制
//        int y = 012; //八进制
//        int z = 0x12; //十六进制
//        System.out.println(""+x); //输出12
//        System.out.println(""+y); //输出10
//        System.out.println(""+z); //输出18
//
//        float f1 = 100000000.0f;
//        System.out.println("" + f1); //输出1.0E8
//        float f2 = 1.3E23f;
//        System.out.println("" + f2); //输出1.3E23

        System.out.println(
                ClassLayout.parseInstance(new Object()).toPrintable());

        char[] chs = new char[2];
        chs[0] = '\uD83D';
        chs[1] = '\uDF01';
        String aa = new String(chs);
        System.out.println(aa.getBytes().length);
        System.out.println(aa.substring(0,1)); //🜁
        System.out.println(aa.substring(1,2)); //🜁
        System.out.println(new String(chs).length()); //🜁

        String s = "\uD83D\uDF01";
        System.out.println(s); //🜁

        char[] chs2 = Character.toChars(0x1F701);
        System.out.println(new String(chs2).length());
        System.out.println(chs2); //🜁

        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc"); //不适用常量池技术
        System.out.println(s1==s2); //打印true
        System.out.println(s1==s3); //打印false
        String s4 = s3.intern();
        System.out.println(s1 == s4); //打印true

    }

    public static void compare(Integer obj1, Integer obj2) {
        Integer obj3 = obj1+1;
        Integer obj4 = obj2+1;
        System.out.println("" + (obj3==obj4));
    }

    public static void f(Integer va) {
        va = 3;
    }
}
