package com.daimens.algorithm.march;

import java.util.Arrays;
import java.util.Scanner;

public class SolutionDay27_L0502 {
	
	static class P implements Comparable<P>{
		int time;
		int index;
		
		P(int time, int index){
			this.time = time;
			this.index = index;
		}

		@Override
		public int compareTo(P o) {
			return this.time - o.time;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {// 注意while处理多个case
			int n = in.nextInt();
			int[][] t = new int[n][2];
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < 2; ++j) {
					t[i][j] = in.nextInt();
				}
			}
			int x = in.nextInt();
			int[][] t2 = new int[1][2];
			for (int i = 0; i < 1; ++i) {
				for (int j = 0; j < 2; ++j) {
					t2[i][j] = in.nextInt();
				}
			}
			
			Arrays.sort(t, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
			
//			P[] ps = new P[n];
//			for (int i = 0; i < n; ++i) {
//				ps[i] = new P(t[i][0] * 60 + t[i][1], i);
//			}
//			Arrays.sort(ps);
			
			int b = t2[0][0] * 60 + t2[0][1];
			int c = b - x;
			int index = -1;
			for (int i = 0; i < n; ++i) {
				int min = t[i][0] * 60 + t[i][1];
				if (min + x <= b) {
					index = i;
				}
				else {
					break;
				}
			}
			System.out.print(t[index][0] + " " + t[index][1]);
		}
		in.close();
	}
}
