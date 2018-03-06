package com.daimens.algorithm.august;

public class SolutionDay27_L0668 {
	
	public int findKthNumber(int m, int n, int k) {
		int lo = 0, hi = 1000000000;
		while (hi - lo > 1){
			int mid = (lo + hi) / 2;
			int cnt = 0;
			for (int i = 1; i <= m; ++i){
				cnt += Math.min(mid / i, n);
			}
			if (cnt < k){
				lo = mid;
			}
			else{
				hi = mid;
			}
		}
		
		return hi;
    }
	
	public static void main(String[] args) {
		SolutionDay27_L0668 day = new SolutionDay27_L0668();
		System.out.println((-15 % 7 + 7) % 7);
	}

}
