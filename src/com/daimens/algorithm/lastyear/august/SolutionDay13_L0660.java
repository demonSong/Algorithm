package com.daimens.algorithm.august;

public class SolutionDay13_L0660 {
	
//	public int newInteger(int n) {
//		return Integer.parseInt(Integer.toString(n, 9));
//    }
	
	public int newInteger(int n) {
		int ans = 0;
		int base = 1;
		
		while (n > 0){
			ans += n % 9 * base;
			n /= 9;
			base *= 10;
		}
		return ans;
	}
	
	public static void main(String[] args) {
		SolutionDay13_L0660 day = new SolutionDay13_L0660();
		System.out.println(day.newInteger(9));
	}

}
