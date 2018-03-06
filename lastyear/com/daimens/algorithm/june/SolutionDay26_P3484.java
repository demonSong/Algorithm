package com.daimens.algorithm.june;

import java.util.Scanner;

public class SolutionDay26_P3484 {
	
	static int MAX_CASE = 1000000;
	static long[] X = new long[MAX_CASE];
	static long[] Y = new long[MAX_CASE];
	static long[] Z = new long[MAX_CASE];
	static long[] C = new long[MAX_CASE];
	static int N = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			String line = in.nextLine();
			if (!line.equals("")){
				String[] nums = line.trim().split(" ");
				X[N] = Long.parseLong(nums[0]);
				Y[N] = Long.parseLong(nums[1]);
				Z[N] = Long.parseLong(nums[2]);
				N++;
			}
			else{
				if (N == 0){
					continue;
				}
				long lst_bit = 0;
				for (int i = 0; i < N; ++i){
					C[i] = (Y[i] - X[i]) / Z[i] + 1;
					lst_bit ^= C[i];
				}
				if ((lst_bit & 1) == 0){
					System.out.println("no corruption");
				}
				else{
					long lf = 0, rt = Long.MAX_VALUE;
					while (lf < rt){
						long mid = lf + (rt - lf) / 2;
						if ((valid(mid) & 1) == 0) lf = mid + 1;
						else rt = mid;
					}
					System.out.println(lf + " " + (valid(lf) - valid(lf -1)));
				}
				N = 0;
				clear();
			}
		}
		in.close();
	}
	
	public static void clear(){
		X = new long[MAX_CASE];
		Y = new long[MAX_CASE];
		Z = new long[MAX_CASE];
		C = new long[MAX_CASE];
	}
	
	public static long valid(long mid){
		long sum = 0;
		for (int i = 0; i < N; ++i){
			if (mid >= Y[i]) sum += C[i];
			else if (mid >= X[i]){
				sum += (mid - X[i]) / Z[i] + 1;
			}
		}
		return sum;
	}
}


