package com.cxypub.demo.sort;

/**
 * @author oaoCoder-徐飞
 * @version V1.0
 * @Title: QuicksortTest
 * @Package com.test
 * @Description: @TODO
 * Copyright: Copyright (c) 2015
 * Company:上海追月信息科技有限公司
 * @date 2016/5/25 10:35
 */
public class QuicksortTest {

	public static void main(String[] args) {
		int score[] = { 67, 69, 75, 87, 89, 90, 99, 100, 21, 55, 10 };
		// partition(score, 0, score.length - 1);
		quicksort(score, 0, score.length - 1);

		System.out.print("最终排序结果：");
		for (int a = 0; a < score.length; a++) {
			System.out.print(score[a] + "\t");
		}

	}

	static void quicksort(int n[], int left, int right) {
		int dp;
		if (left < right) {
			dp = partition(n, left, right);
			quicksort(n, left, dp - 1);
			quicksort(n, dp + 1, right);
			System.out.println(left + "=============" + right);
		}
	}

	static int partition(int n[], int left, int right) {
		int pivot = n[left];
		while (left < right) {
			while (left < right && n[right] >= pivot) {
				right--;
			}
			if (left < right) {
				n[left] = n[right];
			}
			while (left < right && n[left] <= pivot) {
				left++;
			}
			if (left < right) {
				n[right] = n[left];
			}
		}
		n[left] = pivot;
		return left;
	}

	static void print(int score[]) {
		for (int a = 0; a < score.length; a++) {
			System.out.print(score[a] + "\t");
		}
		System.out.println();
	}
}
