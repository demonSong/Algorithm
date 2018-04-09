package com.daimens.algorithm.march;

public class SolutionDay25_L0503 {
	
    public boolean splitArraySameAverage(int[] A) {
    	int sum = 0;
    	int n = A.length;
    	if (n == 0) return true;
    	for (int a : A) sum += a;
    	double avg = sum * 1.0 / n;
    	mem = new int[10000 * 31];
    	return dfs(A, 0, 0, 0, 0, 0);
    }
    

    int[] mem;
    public boolean dfs(int[] A, int pos, int a, int a_c, int b, int b_c) {
    	if (mem[a * a_c] > 0) return true;
    	if (pos == A.length) {
    		double av = a * 1.0 / a_c;
    		double bv = b * 1.0 / b_c;
    		if (Double.compare(av, bv) == 0) {
    			mem[a * a_c] = 1;
    			return true;
    		}
    	}
    	else {
    		if (dfs(A, pos + 1, a + A[pos], a_c + 1, b, b_c) || dfs(A, pos + 1, a, a_c, b + A[pos], b_c + 1)) {
    			mem[a * a_c] = 1;
    			return true;
    		}
    	}
    	mem[a * a_c] = 2;
    	return false;
    }
	
	public static void main(String[] args) {
		SolutionDay25_L0503 day = new SolutionDay25_L0503();
		int[] A = {3, 1};
		System.out.println(day.splitArraySameAverage(A));
	}

}
