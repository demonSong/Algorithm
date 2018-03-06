package com.daimens.algorithm.july;

import java.util.Arrays;
import java.util.Comparator;

public class SolutionDay02_L0220 {
	
	class Pair{
		long num;
		int idx;
		
		public Pair(long num, int idx){
			this.num = num;
			this.idx = idx;
		}
	}
	
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		int n = nums.length;
        if (n <= 1 || t <  0) return false;
		Pair[] ps = new Pair[n];
		for (int i = 0; i < nums.length; ++i) ps[i] = new Pair((long)nums[i], i);
		
		Arrays.sort(ps, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.num < o2.num ? -1 : 1;
			}
		});
		
		int lb = 0, rb = 0;
		for (;;){
			while (rb < n && ps[rb].num - ps[lb].num <= t){
				if (rb != lb && Math.abs(ps[rb].idx - ps[lb].idx) <= k) return true;
				rb++;
			}
			if (rb == n || ps[rb].num - ps[lb].num <= t) break;
			lb++;
		}
		for (int i = lb; i < rb - 1; ++i)
			if (Math.abs(ps[n-1].idx - ps[i].idx) <= k && ps[n-1].num - ps[i].num <= t) return true;
		return false;
    }
	
	public static void main(String[] args) {
		SolutionDay02_L0220 day = new SolutionDay02_L0220();
		int[] nums = {-1,2147483647};
		System.out.println(day.containsNearbyAlmostDuplicate(nums, 1, 2147483647));
	}
}
