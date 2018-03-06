package com.daimens.algorithm.february;

/**
 * 
 * @author DemonSong
 * 313. Super Ugly Number
 * Write a program to find the nth super ugly number.
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
 * For example,
 * [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers 
 * given primes = [2, 7, 13, 19] of size 4.
 * Note:
 * 1. 1 is a super ugly number for any given primes.
 * 2. The given numbers in primes are in ascending order.
 * 3. 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 * 4. The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 *
 */
public class SolutionDay28_313 {
//	public int nthSuperUglyNumber(int n, int[] primes){
//		if (n == 1) return 1;
//		if (n == 2) return primes[0];
//		
//		int[] dp = new int[n];
//		dp[0] = 1;
//		dp[1] = primes[0];
// 		
//		int pos = 1,idx = 1,fac = 1;
//		for (int i = 2; i < n; i++){
//			if(pos < primes.length){
//				if(dp[idx] * dp[fac] < primes[pos]){
//					dp[i] = dp[idx] * dp[fac];
//					fac ++;
//					idx = fac >= n ? ++idx : idx;
//					fac = fac < n ? fac : idx;
//					
//				}else{
//					dp[i] = primes[pos];
//					pos++;
//				}
//			}
//			
//			else{
//				dp[i] = dp[idx] * dp[fac];
//				fac ++;
//				idx = fac >= n ? ++idx : idx;
//				fac = fac < n ? fac : idx;
//			}
//			
//		}
//		
//		return dp[n-1];
//	}
	
	public int nthSuperUglyNumber(int n, int[] primes){
		int[] ugly = new int[n];
		int[] idx = new int[primes.length];
		
		ugly[0] = 1;
		for (int i = 1; i < n; i++){
			ugly[i] = Integer.MAX_VALUE;
			
			//find next 解决了重复的问题
			for (int j = 0; j < primes.length; j++){
				ugly[i] = Math.min(ugly[i], primes[j] * ugly[idx[j]]);
			}
			
			//slip duplicate
			for (int j = 0; j < primes.length; j++){
				while(primes[j] * ugly[idx[j]] <= ugly[i]) idx[j] ++;
			}
		}
		
		return ugly[n-1];
	}
	
	public static void main(String[] args) {
		SolutionDay28_313 day = new SolutionDay28_313();
		int[] primes = {2,7,13,19};
		day.nthSuperUglyNumber(12, primes);
	}
}
