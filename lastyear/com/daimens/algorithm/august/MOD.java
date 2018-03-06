package com.daimens.algorithm.august;

public class MOD {
	
	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5,6,7,8,9,10};
		int step = -2;
		for (int now = 1; now <= 9; ++now)
			System.out.println((now - 1 - (-step) % 9 + 9) % 9 + 1);
		
//		int step = 2;
//		for (int now = 1; now <= 10; ++now){
//			System.out.println((now - 1 + step) % 10 + 1);
//		}
	}

}
