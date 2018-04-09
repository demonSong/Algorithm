package com.daimens.algorithm.april;

public class SolutionDay08_L0813 {
	
    public double largestSumOfAverages(int[] A, int K) {
    	int n = A.length;
    	int[] sums = new int[n + 1];
    	for (int i = 0; i < n; ++i) {
    		sums[i + 1] = sums[i] + A[i];
    	}
    	mem = new double[A.length][K + 1];
    	return f(A, sums, 0, K);
    }
    
    
    double[][] mem;
    public double f(int[] A, int[] S, int pos, int K) {
    	if (mem[pos][K] > 0) return mem[pos][K];
    	if (K == 1) {
    		return (S[A.length] - S[pos]) * 1.0 / (A.length - pos);
    	}
    	double res = 0;
    	for (int i = pos; i < A.length - (K - 1); ++i) {
    		double a1 = (S[i + 1] - S[pos]) * 1.0 / (i + 1 - pos);
    		res = Math.max(res, a1 + f(A, S, i + 1, K - 1));
    	}
    	mem[pos][K] = res;
    	return res;
    }
    
   public static void main(String[] args) {
		SolutionDay08_L0813 day = new SolutionDay08_L0813();
		int[] A = {4, 1, 7, 5, 6, 2, 3};
		System.out.println(day.largestSumOfAverages(A, 4));
	}

}
