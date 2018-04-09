package com.daimens.algorithm.april;

import java.util.Scanner;

public class SolutionDay05_L0500 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			System.out.println(n / (2 * m) * m * m);
		}
		in.close();
	}
}
