package com.daimens.algorithm.july;

public class SolutionDay02_L0633 {
	
//	public boolean judgeSquareSum(int c) {
//		for (int i = 0; (long) i * i <= c; ++i) {
//        	int x = (int) Math.sqrt(c - i * i);
//        	if (x * x + i * i == c) return true;
//        }
//        return false;
//    }
	
	public boolean judgeSquareSum(int c) {
		int n = (int) Math.sqrt(c) + 1;
		int lf = 0, rt = n;
		while (lf <= rt){
			if (rt * rt + lf * lf > c){
				rt --;
			}
			else if (lf * lf + rt * rt < c){
				lf ++;
			}
			else return true;
		}
		return false;
	}
	
//	public boolean valid (int c, int b){
//		int a = (int) Math.sqrt(c - b * b);
//		return a * a + b * b == c;
//	}
	
	public boolean binarySearch(int tar, int c){
		int lf = 0, rt = c;
		while (lf < rt){
			int mid = lf + (rt - lf) / 2;
			if ((mid != 0 && mid  < tar / mid) || (mid == 0 && 0 < tar)) lf = mid + 1;
			else rt = mid;
		}
		if (rt * rt == tar) return true;
		return false;
	}
	
	public static void main(String[] args) {
		SolutionDay02_L0633 day = new SolutionDay02_L0633();
		System.out.println(day.judgeSquareSum(Integer.MAX_VALUE));
	}
}
