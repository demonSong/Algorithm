package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 441.Arranging Coins
 * You have a total of n coins that you want to form in a staircase shape,
 * where every k-th row must have exactly k coins.
 * Given n, find the total number of full staircase rows that can be formed.
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 * Example 1:
 * n = 5
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤
 * Because the 3rd row is incomplete, we return 2.
 * 
 * Example 2:
 * n = 8
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 * Because the 4th row is incomplete, we return 3.
 *
 */
public class SolutionDay20_441 {
	public int arrangeCoins(int n){
		int sum =0;
		int i =1;
		while(true){
			sum += i;
			if (sum >=n){
				return i;
			}
			i++;
		}
		
	}
	
	public static void main(String[] args) {
		SolutionDay20_441 day20_441 = new SolutionDay20_441();
		day20_441.arrangeCoins(5);
	}
}
