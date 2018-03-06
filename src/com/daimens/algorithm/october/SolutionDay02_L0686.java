package com.daimens.algorithm.october;

public class SolutionDay02_L0686 {
	
	public int repeatedStringMatch(String A, String B) {
		int nb = B.length();
		int na = A.length();
		int times = nb / na + 2;
		StringBuilder sb = new StringBuilder(A);
		for (int i = 1; i <= times; ++i) {
			if (sb.toString().contains(B)) return i;
			else {
				sb.append(A);
			}
		}
		return -1;
    }
	
	public static void main(String[] args) {
		SolutionDay02_L0686 day = new SolutionDay02_L0686();
	}
	
}
	