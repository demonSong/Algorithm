package com.daimens.algorithm.july;

import java.util.Scanner;

/*
ID: demon.s1
LANG: JAVA
TASK: test
*/
public class SolutionDay25_U1102 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			String first = in.nextLine();
			String second = in.nextLine();
			System.out.println((solve(first, second) ? "GO" : "STAY"));
		}
		in.close();
	}
	
	public static boolean solve(String fir, String sec){
		int a1 = 1;
		for (char c : fir.toCharArray()){
			int x = c - 'A' + 1;
			a1 = (a1 * x) % 47;
		}
		int a2 = 1;
		for (char c : sec.toCharArray()){
			int x = c - 'A' + 1;
			a2 = (a2 * x) % 47;
		}
		return a1 == a2;
	}

}
