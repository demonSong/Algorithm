package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 172.Factorial Trailing Zeroes
 * Given an integer n,return the number of trailing zeroes in n!.
 *
 */
public class SolutionDay26_172 {
	
	public int trailingZeroes(int n) {   
        if(n<1) return 0;   
        int c = 0;   
           
        while(n/5 != 0) {    
            n /= 5;   
            c += n;   
        }   
           
        return c;   
    }   

	public static void main(String[] args) {
		SolutionDay26_172 day26_172 = new SolutionDay26_172();
		day26_172.trailingZeroes(13);
	}
}
