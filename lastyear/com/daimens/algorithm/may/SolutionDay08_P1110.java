package com.daimens.algorithm.may;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         1110.求m区间内的最小值
 * 
 *         题目描述
 *         一个含有n项的数列(1<=n<=2000000)，求出每一项前的m个数到它这个区间内(本身不算)的最小值。若前面的数不足m项则从第1个数开始，若前面没有数则输出0。
 * 
 *         输入 第一行一个整数T(<=10)，表示有T组测试数据
 * 
 * 
 *         第一行两个数n，m (1 <= n,m <= 2000000)。
 * 
 *         第二行，n个正整数，为所给定的数列(所有正整数 <= 2000000)
 * 
 *         输出 n行，第i行的一个数ai，为所求序列中第i个数前m个数的最小值
 * 
 *         样例输入 1 6 2 7 8 1 4 3 2 样例输出 0 7 7 1 1 3
 *
 */
public class SolutionDay08_P1110 {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int i = 0; i < T; i++){
			int n = in.nextInt();
			int m = in.nextInt();
			int[] a = new int[n];
			for (int j = 0; j < n; j++) {
				a[j] = in.nextInt();
			}
			solve(a,m);
		}
		in.close();
	}
	
//	private static void solve(int[] a, int m){
//		for (int i = 0; i < a.length; i++){
//			int min = Integer.MAX_VALUE;
//			for (int j = i-1; j >= Math.max(0, i-m); j--){
//				min = Math.min(min, a[j]);
//			}
//			System.out.println(min == Integer.MAX_VALUE ? 0 : min);
//		}
//	}
	
	private static void solve(int[] a, int m){
		int n = a.length;
		int[] q = new int[n];
		int[] f = new int[n];
		f[0] = 0;
		int front = 0, rear = 0;
		for(int i = 0; i < n-1; i++){
			//队列中最小元素的坐标小于i-m+1，直接删除，不再需要
			if (front <= rear && (q[front] < (i - m+1))) front++; //删除
			while (front <= rear && a[i] <= a[q[rear]]) rear--; //加入  
			// a[i] > a[q[rear]] 加入的元素为单调递增， 从队尾加入单调递增的元素
			q[++rear] = i;
			// 取的元素一定是头
			f[i+1] = a[q[front]];
		}
		
		for (int i = 0; i < n; i++){
			System.out.println(f[i]);
		}
	}
}
