package com.daimens.algorithm.september;

import java.util.Scanner;

public class SolutionDay10_A0001 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			int sum = a + b + c;
			int min = Math.min(a, Math.min(b, c));
			int max = Math.max(a, Math.max(b, c));
			int mid = sum - min - max;
			if (min + mid > max) System.out.println(min + max + mid);
			else {
				System.out.println(2 * (min + mid) - 1);
			}
		}
		in.close();
	}

}
