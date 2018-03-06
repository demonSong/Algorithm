package com.daimens.algorithm.september;

import java.util.TreeSet;

public class SolutionDay24_L0683 {

//	public int kEmptySlots(int[] flowers, int k) {
//		int n = flowers.length;
//		if (n == 1 && k == 0) return 1;
//		sort = new int[n + 16];
//		for (int i = 0; i < n; ++i) {
//			int index = add(flowers[i]);
//			int min = index - 1 < 0 ? -11111111 : sort[index - 1];
//			int max = index + 1 >= tot ? -11111111 : sort[index + 1];
//			if (valid(flowers[i], k, min, max)) return i + 1;
//		}
//		return -1;
//	}
//
//	boolean valid(int x, int k, int min, int max) {
//		if (max - x == k + 1) return true;
//		if (x - min == k + 1) return true;
//		return false;
//	}
//
//	int[] sort;
//	int tot = 0;
//	public int add(int x) {
//		int j = 0;
//		while (j < tot && sort[j] < x) {
//			++j;
//		}
//		for (int i = tot - 1; i >= j; --i) {
//			sort[i + 1] = sort[i];
//		}
//		sort[j] = x;
//		tot++;
//		return j;
//	}
	
	public int kEmptySlots(int[] flowers, int k) {
		int n = flowers.length;
		if (n == 1 && k == 0) return 1;
		TreeSet<Integer> sort = new TreeSet<>();
		for (int i = 0; i < n; ++i) {
			sort.add(flowers[i]);
			Integer min = sort.lower(flowers[i]);
			Integer max = sort.higher(flowers[i]);
			int mi = min == null ? -1111111 : min;
			int ma = max == null ? -1111111 : max;
			if (valid(flowers[i], k, mi, ma)) return i + 1;
		}
		return -1;
	}

	boolean valid(int x, int k, int min, int max) {
		if (max - x == k + 1) return true;
		if (x - min == k + 1) return true;
		return false;
	}
	
	// int[] sort;
	// int tot = 0;
	// public int add(int x) {
	//// if (tot == 0) {
	//// sort[0] = x;
	//// tot ++;
	//// return 0;
	//// }
	//// else {
	// int j = 0;
	// while (j < tot && sort[j] < x) {
	// ++j;
	// }
	// for (int i = tot - 1; i >= j; --i) {
	// sort[i + 1] = sort[i];
	// }
	// sort[j] = x;
	// tot ++;
	// return j;
	//// }
	// }

	public static void main(String[] args) {
		SolutionDay24_L0683 day = new SolutionDay24_L0683();
		int[] flowers = { 5, 39, 25, 28, 16, 58, 70, 29, 67, 24, 78, 13, 9, 64, 98, 38, 44, 96, 27, 88, 75, 84, 69, 34,
				55, 12, 47, 33, 77, 15, 31, 74, 2, 26, 76, 10, 17, 72, 100, 36, 6, 90, 4, 95, 49, 21, 94, 79, 62, 32, 1,
				35, 60, 18, 3, 53, 82, 48, 54, 30, 19, 87, 40, 85, 68, 57, 11, 42, 92, 61, 71, 37, 14, 51, 50, 83, 22,
				93, 91, 65, 99, 52, 7, 46, 89, 80, 20, 8, 97, 86, 23, 66, 81, 59, 56, 63, 43, 41, 73, 45 };
		int k = 4;
		System.out.println(day.kEmptySlots(flowers, k));
	}
}
