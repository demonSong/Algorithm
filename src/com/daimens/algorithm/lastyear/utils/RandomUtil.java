package com.daimens.algorithm.utils;

import java.util.Random;

/**
 * 
 * @author Demon Song
 *
 */
public abstract class RandomUtil {

	public static int[] randomSet(int min, int max, int n) {
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		while (count < n) {
			int num = (int) (Math.random() * (max - min) + min);
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if (num == result[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result[count] = num;
				count++;
			}
		}
		return result;
	}
	
	public static int[] randomSetWithNoDuplicate(int min, int max, int n){
		return randomSet(min, max, n);
	}
	
	public static int[] randomSetWithDuplicate(int min, int max, int n){
		int[] res = new int[n];
		for (int i = 0; i < n; i++){
			int num = (int)(Math.random() * (max - min) + min);
			res[i] = num;
		}
		return res;
	}
	
	public static long[] randomLongArray(int length, int size){
		long[] random = new long[size];
		Random rand = new Random();
		for (int i = 0; i < size; i++){
			random[i] = (long) rand.nextInt((int)Math.pow(10, length-1)) + (long)Math.pow(10, length-1) * (int)(Math.random() * 9 + 1);
		}
		return random;
	}
	
	public static void main(String[] args) {
		int[] set = RandomUtil.randomSetWithDuplicate(0, 18, 20);
		for (int num : set){
			System.out.println(num);
		}
	}
}
