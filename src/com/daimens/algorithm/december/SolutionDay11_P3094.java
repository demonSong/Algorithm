package com.daimens.algorithm.december;

import java.util.Scanner;

public class SolutionDay11_P3094 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			String line = in.nextLine();
			if (line.equals("#")) break;
			int sum = 0;
			for (int i = 0; i < line.length(); ++i) {
				if (line.charAt(i) == ' ') continue;
				sum += (i + 1) * (line.charAt(i) - 'A' + 1);
			}
			System.out.println(sum);
		}
		in.close();
	}
}

