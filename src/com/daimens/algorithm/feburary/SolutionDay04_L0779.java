package com.daimens.algorithm.feburary;

public class SolutionDay04_L0779 {
	
	public int kthGrammar(int N, int K) {
		if (N == 1) return 0;
		int len = 1 << (N - 1);
		len /= 2;
		if (K <= len) return kthGrammar(N - 1, K);
		else {
			int val = kthGrammar(N - 1, K - len);
			return val ^ 1;
		}
	}

	public static void main(String[] args) {
		SolutionDay04_L0779 day = new SolutionDay04_L0779();
		System.out.println(day.kthGrammar(4, 5));
	}
}
