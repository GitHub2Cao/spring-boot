package com.softnovo.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author cgm
 * @date 2023-12-09 22:40
 */
public class CRUD {

	List<Integer> lists = new ArrayList<>();



	AtomicBoolean[] cache = new AtomicBoolean[2 << 32];

	public synchronized boolean isIPExist(String inputIP) {
		if (inputIP == null || inputIP.isEmpty()) {
			throw new RuntimeException("不合法");
		}
		String[] splitIp = inputIP.split("\\.");
		int n = Integer.parseInt(splitIp[0]);
		for (int j = 1; j < splitIp.length; j++) {
			n = n << 8;
			n += Integer.parseInt(splitIp[j]);
		}

		return cache[n].get();
	}


	private static void getMinCoinCountOfValue() {
		// 硬币面值
		int[] values = {5, 3};
		// 总价
		int total = 11;
		int minCoinCount = getMinCoinCountOfValueHelper(total, values);

		// 输出结果
		System.out.println(minCoinCount);
	}

	/**
	 * @param total 金额
	 * @param coins 币种数组，从大到小排序
	 * @return 返回币数，如果返回-1表示无法凑够total
	 */
	private static int getMinCoinCountOfValueHelper(int total, int[] coins) {
		if (coins.length == 0) {
			return -1;
		}

		//当前币值
		int currentCoin = coins[0];

		//使用当前币值数量
		int useCurrentCoinCount = total / currentCoin;

		int restTotal = total - useCurrentCoinCount * currentCoin;
		// 如果restTotal为0，表示余额已除尽，组合完成
		if (restTotal == 0) {
			return useCurrentCoinCount;
		}

		// 其他币种数量
		int coninCount = -1;
		// 剩余的币种
		int[] restCoins = Arrays.copyOfRange(coins, 1, coins.length);
		while (useCurrentCoinCount >= 0) {
			// 否则尝试用剩余面值求当前余额的硬币总数
			coninCount = getMinCoinCountOfValueHelper(restTotal, restCoins);

			// 如果后续没有有可用组合,退一步，当前useCurrentCoinCount币数减1
			if (coninCount == -1) {
				// 否则尝试把当前面值数-1
				useCurrentCoinCount--;
				// 重新计算restTotal
				restTotal = total - useCurrentCoinCount * currentCoin;

			} else {
				return useCurrentCoinCount + coninCount;
			}
		}

		return -1;
	}


	public static void main(String[] args) {
		int i = 0;
		while (true) {

			i += 1;
			System.out.println(i);
			if (i == 0) {
				System.out.println(i);
				break;
			}
		}




//		int[] intArray = {1, 2, 3, 4};
//		int[] intArray2 = new int[3];
//		intArray2[0] = 1;
//		intArray2[1] = 3;
//		intArray2[2] = 5;
//		int[] intArray3 = new int[] {2, 3, 6, 7};
//		System.out.println(Arrays.toString(intArray));
//		System.out.println(Arrays.toString(intArray2));
//		System.out.println(Arrays.toString(intArray3));
//
//		int[] newIntArray = new int[10];
//		Arrays.fill(newIntArray, 100);
//		System.out.println(Arrays.toString(newIntArray));
//
//		int[] a = {5, 7, 20};
//		System.out.println("a的长度为:" + a.length);//3
//
//		//整型变量，存放在栈中
//		int num = 8;
//		System.out.println("num:" + num);
//
//		//定义一个新的数组b
//		int[] b = new int[4];
//		System.out.println("b的长度是:" + b.length);
//
//		//将a赋值给b，是b的指向改变了，但b原先对应的数组依然存在
//		b = a;
//		System.out.println("b的长度是:" + b.length);
//
//
//
//		a = Arrays.copyOf(a, a.length * 2 + 1);
//		System.out.println(a.length);
//		System.out.println(Arrays.toString(a));
	}




}
