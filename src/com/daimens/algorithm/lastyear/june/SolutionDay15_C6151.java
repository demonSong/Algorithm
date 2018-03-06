package com.daimens.algorithm.june;

import java.util.Scanner;

public class SolutionDay15_C6151 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x1 = in.nextInt();
		int y1 = in.nextInt();
		int x2 = in.nextInt();
		int y2 = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		
		int a1 = Math.abs(x2 - x1);
		int b1 = Math.abs(y2 - y1);
		
		
		if (b1 % b == 0 && a1 % a == 0 && (a1 / a) % 2 == (b1 / b) % 2) System.out.println("YES");
		else System.out.println("NO");
		
//		if (b1 % b != 0 || a1 % a != 0) System.out.println("NO");
//		
//		int k = b1 / b;
//		
//		//even
//		if (k % 2 == 0){
//			if (a1 == 0 || (a1 / a) % 2 == 0) System.out.println("YES");
//			else System.out.println("NO");
//		}
//		else{
//			if ((a1 / a) % 2 == 1) System.out.println("YES");
//			else System.out.println("NO");
//		}
		in.close();
	}
}
