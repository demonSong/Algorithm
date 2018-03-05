package com.daimens.algorithm.january;

public class SolutionDay12_L0762 {
	
    public int countPrimeSetBits(int L, int R) {
    	boolean[] isPrime = new boolean[32];
    	int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
    	for (int prime : primes) isPrime[prime] = true;
    	int ans = 0;
    	for (int i = L; i <= R; ++i) {
    		if (isPrime[Integer.bitCount(i)]) ans += 1;
    	}
    	return ans;
    }
    
	public static void main(String[] args) {
		SolutionDay12_L0762 day = new SolutionDay12_L0762();
		System.out.println();
	}

}
