package com.daimens.algorithm.sort;

import com.daimens.algorithm.utils.RandomUtil;

public class BucketSort {
	
	public static void bucketSortWithNoDuplicate(int[] data){
		int n = data.length;
		int[] aux = new int[n];
		int[] map = new int[4 * n]; //开辟足够大的空间
		for (int i = 0; i < n; i++){
			map[data[i]]++; 
		}
		
		for (int i = 0, k = 0; i < 4 * n; i++){
			if (map[i] == 0) continue;
			aux[k++] = i;
		}
		
		for (int i = 0; i < n; i++){
			data[i] = aux[i];
		}
	}
	
	public static void bucketSortWithDuplicate(int[] data){
		int n = data.length;
		int[] map = new int[11];
		int[] aux = new int[n];
		
		for (int i = 0; i < n; i++){
			map[data[i]+1]++;
		}
		
		for (int i = 1; i < map.length; i++){
			map[i] = map[i-1] + map[i];
		}
		
		for (int i = 0; i < n; i++){
			aux[map[data[i]]++] = data[i];
		}
		
		for (int i = 0; i < n; i++){
			data[i] = aux[i];
		}
	}
	
	public static void radixSort(long[] data){
		int n = data.length;
		int exp = 1;
		int radix = 10;
		long max = data[0];
		
		while (max/exp != 0){
			int[] map = new int[11];
			long[] aux = new long[n];

			for (int i = 0; i < n; i++){
				map[(int)(data[i] / exp % 10) + 1]++;
			}
			
			for (int i = 1; i < map.length; i++){
				map[i] += map[i-1];
			}
			
			for (int i = 0; i < n; i++){
				aux[map[(int)(data[i] / exp % 10)]++] = data[i];
			}
			
			for (int i = 0; i < n; i++){
				data[i] = aux[i];
			}
			exp *= radix;
		}
	}
	
	public static void radixSort(int[] data){
		int n = data.length;
		int exp = 1;
		int radix = 10;
		int max = 111111111;
		
		while (max/exp != 0){
			int[] map = new int[11];
			int[] aux = new int[n];

			for (int i = 0; i < n; i++){
				map[(int)(data[i] / exp % 10) + 1]++;
			}
			
			for (int i = 1; i < map.length; i++){
				map[i] += map[i-1];
			}
			
			for (int i = 0; i < n; i++){
				aux[map[(int)(data[i] / exp % 10)]++] = data[i];
			}
			
			for (int i = 0; i < n; i++){
				data[i] = aux[i];
			}
			exp *= radix;
		}
	}
	
	public static void main(String[] args) {
		int[] testNoDuplicate = RandomUtil.randomSetWithNoDuplicate(0, 10, 9);
		for (int num : testNoDuplicate) System.out.println(num);
		BucketSort.bucketSortWithNoDuplicate(testNoDuplicate);
		System.out.println("----------------排序后----------------");
		for (int num : testNoDuplicate) System.out.println(num);
		System.out.println("----------------分割线----------------");
		int[] testWithDuplicate = RandomUtil.randomSetWithDuplicate(0, 10, 20);
		for (int num : testWithDuplicate) System.out.println(num);
		BucketSort.bucketSortWithDuplicate(testWithDuplicate);
		System.out.println("----------------排序后----------------");
		for (int num : testWithDuplicate) System.out.println(num);
		System.out.println("----------------分割线----------------");
		long[] test = RandomUtil.randomLongArray(9, 10);
		for (long num : test) System.out.println(num);
		System.out.println("----------------排序后----------------");
		BucketSort.radixSort(test);
		for (long num : test) System.out.println(num);
		//不同位数的排序
		System.out.println("----------------不同位数排序----------------");
		int[] data = RandomUtil.randomSetWithDuplicate(0, 10000, 20);
		BucketSort.radixSort(data);
		for (long num : data) System.out.println(num);
	}
}
