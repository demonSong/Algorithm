package com.daimens.algorithm.september;

import java.util.Scanner;

public class SolutionDay08_D0002 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String str = in.next();
			char[] cs  = str.toCharArray();
			long res = 1;
			int max = 0;
			for (int i = 0, t = 0; i < cs.length; ++i) {
				if (cs[i] == '(') {
					t++;
					max = Math.max(max, t);
				}
				else if (cs[i] == ')') {
					t--;
					if (t == 0) {
						res *= factor(max);
						max = 0;
					}
				}
			}
			if (str.length() == 0) System.out.println(0);
			else System.out.println(res);
		}
		in.close();
	}
	
	public static long factor(int n) {
		long res = 1;
		for (int i = 1; i <= n; ++i) {
			res *= i;
		}
		return res;
	}
	
}
