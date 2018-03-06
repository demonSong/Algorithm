package com.daimens.algorithm.september;

import java.util.Scanner;

public class SolutionDay08_D0001 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String str = in.next();
			int n = str.length();
			int j = -1;
			for (int i = 1; i < n; ++i) {
				if (str.substring(0, i).equals(str.substring(n - i))) {
					j = i;
				}
			}
			if (j == -1) System.out.println(str + str);
			else System.out.println(str.substring(0, n - j) + str);
		}
		in.close();
	}

}
