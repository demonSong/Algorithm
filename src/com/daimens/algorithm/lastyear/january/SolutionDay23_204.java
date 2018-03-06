package com.daimens.algorithm.january;


/**
 * 
 * @author Demon Song
 * 204.Count Primes
 * Description:
 * Count the number of prime numbers less than a non-negative number n.
 *
 */
public class SolutionDay23_204 {
	
	public int countPrimes(int n){
		boolean[] isPrime = new boolean[n];
		//0 or 1 is not prime.
		for (int i = 2; i < n; i++){
			isPrime[i] = true;
		}
		
		for (int i = 2; i * i < n; i++) {
			if(!isPrime[i]) continue;
			for (int j = i * i; j < n; j += i){
				isPrime[j] = false;
			}
		}
		
		int count = 0;
		for (int i = 2; i < n; i++ ){
			if(isPrime[i]) count++;
		}
		return count;
	}
	
}
