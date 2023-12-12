package com.softnovo.algorithm.array;

import java.util.Arrays;

/**
 * @author cgm
 * @date 2023-12-09 22:40
 */
public class CRUD {
	public static void main(String[] args) {
		int[] intArray = {1, 2, 3, 4};
		int[] intArray2 = new int[3];
		intArray2[0] = 1;
		intArray2[1] = 3;
		intArray2[2] = 5;
		int[] intArray3 = new int[] {2, 3, 6, 7};
		System.out.println(Arrays.toString(intArray));
		System.out.println(Arrays.toString(intArray2));
		System.out.println(Arrays.toString(intArray3));

		int[] newIntArray = new int[10];
		Arrays.fill(newIntArray, 100);
		System.out.println(Arrays.toString(newIntArray));

		int[] a = {5, 7, 20};
		System.out.println("a的长度为:" + a.length);//3

		//整型变量，存放在栈中
		int num = 8;
		System.out.println("num:" + num);

		//定义一个新的数组b
		int[] b = new int[4];
		System.out.println("b的长度是:" + b.length);

		//将a赋值给b，是b的指向改变了，但b原先对应的数组依然存在
		b = a;
		System.out.println("b的长度是:" + b.length);



		a = Arrays.copyOf(a, a.length * 2 + 1);
		System.out.println(a.length);
		System.out.println(Arrays.toString(a));
	}




}
