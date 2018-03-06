package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 357.Count Number with unique Digits
 * Given a non-negative integer n,count all numbers with unique digits,x,where 0 ≤ x < 10^n.
 * Example:
 * Given n = 2,return 91.(The answer should be the total numbers in the range of 0 ≤ x < 100, 
 * excluding [11,22,33,44,55,66,77,88,99])
 *
 */
public class SolutionDay03_357 {
	
	public int countNumbersWithUniqueDigits(int n){
		if(n == 0) return 1;
		int result = 10,times = 9;
		for(int i = 1; i < n && n <= 10; i++){	
			times *= (10-i);
			result += times;
		}
		return result;
	}

}
