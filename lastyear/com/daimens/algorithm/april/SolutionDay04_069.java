package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 69.Sqrt(x)
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 *
 */
public class SolutionDay04_069 {
	
//	public int mySqrt(int x) {
//		
//		if(x <= 0) return 0;
//		
//		int i = 0, max = 0;
//		for (i = (int) Math.pow(10, Math.log10(x) / 2); i * i <= x; i++) {
//			if (i * i >= max) {
//				max = i * i;
//			} else {
//				return i - 1;
//			}
//		}
//		
//		return i-1;
//    }
	
	public int mySqrt(int x) {
		if (x <= 0) return 0;
		
		int left = 1, right = x;

		int ans = 0;
		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (mid > x / mid) { //避免溢出
				right = mid - 1;
			} else {

				left = mid + 1;
				ans = mid;
			}
		}

		return ans;
	}
	
	public static void main(String[] args) {
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(46340 * 46340);
	}

}
