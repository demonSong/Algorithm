package com.daimens.algorithm.december;


/**
 * 
 * @author Demon Song
 * 9.Palindrome Number
 * Determine whether an integer is a palindrome.Do this without extra space.
 *
 */
public class SolutionDay28_9 {
	public boolean isPalindrome(int x){
		if(x < 0)
			return false;
		if(x ==0)
			return true;
		int base =1;
		while(x/base >=10){
			base *=10;
		}
		while(x!=0){
			int leftDigit = x /base;
			int rightDigit = x % 10;
			if(leftDigit != rightDigit){
				return false;
			}
			x -= base*leftDigit;
			base /=100;
			x /=10;
		}
		
		return true;
	}
}
