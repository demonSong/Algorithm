package com.daimens.algorithm.november;

/**
 * 
 * @author Demon Song
 * 231.Power of Two
 * Given an integer,write a function to determine
 * if it is a power of two
 *
 */
public class SolutionDay30 {
	public boolean isPowerOfTwo(int n){

		//每次除都要保证n为偶数，否则直接跳出
		while ( n !=1){
			if(n % 2 ==0){ //n为偶数
				n = n/2;
			}
			else{
				return false;
			}
		}
		
		return true;
	}
}
