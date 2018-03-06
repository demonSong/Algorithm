package com.daimens.algorithm.june;

import java.util.PriorityQueue;
import java.util.Scanner;

public class SolutionDay01_C4172 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int S = in.nextInt();
		int[] goods = new int[N];
		for (int i = 0; i < N; i++){
			goods[i] = in.nextInt();
		}
		solve(N, S, goods);
		in.close();
	}
	
	private static class Pair{
		int index;
		int value;
		Pair(int index, int value){
			this.index = index;
			this.value = value;
		}
	}
	
	private static void solve(int N, int S, int[] goods){
		int sum = 0;
		for (int cost : goods){
			sum += cost;
		}
		int tol = sum + N * (N + 1) * N / 2;
		if (tol <= S){
			System.out.println(N + " " + tol);
			return;
		}
		
		PriorityQueue<Pair> queue = new PriorityQueue<>();
		for (int i = N - 1; i >= 1; i--){
		
		}
		
		for (int i = N-1; i >= 1; i--){
			sum -= goods[i];
			int total = i * (1 + i) * i / 2 + sum;
			if (total <= S){
				System.out.println(i + " " + total);
				return;
			}
		}
		
		System.out.println("0 0");
	}

}
