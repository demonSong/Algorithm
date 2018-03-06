package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 367.Valid Perfect Square
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * Note: Do not use any built-in library function such as sqrt.
 * 
 * Example 1:
 * Input:16
 * Returns: True
 * 
 * Example 2:
 * Input:14
 * Returns: False
 *
 */
public class SolutionDay06_367 {
	public boolean isPerfectSquare(int num) {
		
		if (num == 0) return false;
		
		int end = binarySearch(num, num);
		
        return end == -1 ? false : true;
    }
	
	private int binarySearch(int num, int target){
		
		int lf = 1, rt = num;  //闭区间
		
		
		while (lf < rt){
			
			int mid = lf + (rt - lf) / 2; //防止溢出
			
			if (target / mid > mid){
				lf = mid + 1;
			}else{
				rt = mid;
			}
			
		}
		
		if (target / rt == rt) return rt;
		
		return -1;
	}
}
