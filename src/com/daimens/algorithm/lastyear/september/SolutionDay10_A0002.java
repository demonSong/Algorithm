package com.daimens.algorithm.september;

import java.util.Scanner;

public class SolutionDay10_A0002 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int s = in.nextInt();
			int[] f = new int[n];
			for (int i = 0; i < n; ++i) f[i] = in.nextInt();
			int cnt = 0;
			for (int i = 0; i < n; ++i) {
				if (s >= f[i]) cnt++;
				s -= f[i];
			}
			System.out.println(cnt);
		}
		in.close();
	}

}
