package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 507.Perfect Number
 * We define the perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.
 * Now, given an integer n, write a function that returns true when it is a perfect number and false when it it not.
 * 
 * Example:
 * Input : 28
 * Output : True
 * Explanation: 28 = 1 + 2 + 4 + 7 + 14
 * Note:The input number n will not exceed 100,000,000. (1e8)
 *
 */
public class SolutionDay26_507 {
	
	public boolean checkPerfectNumber(int num) {
		
		if(num == 0 || num == 1) return false;
		
		int sum = 1;
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0){
				sum += i;
				sum += num / i;
			}
		}
		
        return sum == num;
    }
	
	
	public static void main(String[] args) {
		SolutionDay26_507 day = new SolutionDay26_507();
	}

}
