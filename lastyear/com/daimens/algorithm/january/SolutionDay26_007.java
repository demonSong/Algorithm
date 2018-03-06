package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 7.Reverse Integer
 * Reverse digits of an integer.
 * Example 1:
 * x = 123,return 321
 * Example 2:
 * x = -123,return -321
 *
 */
public class SolutionDay26_007 {

	public int reverse(int x){
		long ret = 0;
		int sign = (x<0)?-1:1;
		x = Math.abs(x);
		while(x !=0){
			int digit = x % 10;
			x = x/10;
			ret = ret * 10 + digit;
		}
		
		ret *= sign;
		if(ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE){
			return 0;
		}
		else{
			return (int) ret;
		}
	}
	
	public static void main(String[] args) {
		SolutionDay26_007 day27_007 = new SolutionDay26_007();
		day27_007.reverse(1);
	}
}
