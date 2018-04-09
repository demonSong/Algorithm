package com.daimens.algorithm.march;

import java.util.Scanner;

public class SolutionDay29_L0500 {
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String a = in.nextLine().trim();
			int n = a.length();
			int max = -1;
			String ans = "";
			for (int i = 1; i < n; ++i) {
				String tmp = a.substring(0, i);
				if (n % i == 0) {
					boolean ok = true;
					for (int j = i; j < n; j +=i) {
						if (!tmp.equals(a.substring(j, j + i))) {
							ok = false;
						}
					}
					if (ok) {
						if (tmp.length() > max) {
							ans = tmp;
							max = tmp.length();
						}
					}
				}
			}
			
			if (max != -1) System.out.println(ans);
			else System.out.println("false");
		}
		in.close();
	}
}
