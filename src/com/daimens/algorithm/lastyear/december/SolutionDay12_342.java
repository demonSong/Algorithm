package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 342.power of four
 * Given an integer,write a function to check whether
 * it is a power of 4.
 * Example:
 * Given num =16,return true.Given num =5,return false.
 *
 */
public class SolutionDay12_342 {
	public boolean isPowerOfFour(int num) {
        if (num < 1) {
            return false;
        }

        while (num % 4 == 0) {
            num /= 4;
        }

        return num == 1;  
    }
}
