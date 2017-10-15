package com.daimens.algorithm.october;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SolutionDay15_L0697 {
	
    public int findShortestSubArray(int[] nums) {
    	Map<Integer, Integer> map = new HashMap<>();
    	int max = 0;
    	for (int num : nums) {
    		map.put(num, map.getOrDefault(num, 0) + 1);
    	}
    	
    	for (int key : map.keySet()) {
    		max = Math.max(max, map.get(key));
    	}
    	
    	int ans = 1 << 29;
    	Map<Integer, Integer> cnt = new HashMap<>();
    	int lf = 0, rt = 0;
    	int window = 0;
    	for (;;) {
    		while (rt < nums.length && window != max) {
    			cnt.put(nums[rt], cnt.getOrDefault(nums[rt], 0) + 1);
    			window = Math.max(window, cnt.get(nums[rt]));
    			rt ++;
    		}
    		if (window != max) break;
    		ans = Math.min(ans, rt - lf);
    		cnt.put(nums[lf], cnt.get(nums[lf]) - 1);
    		lf ++;
    		window = Collections.max(cnt.values());
    	}
    	return ans;
    }
    
//    public int findShortestSubArray(int[] nums) {
//    	Map<Integer, Integer> lf  = new HashMap<>();
//    	Map<Integer, Integer> rt  = new HashMap<>();
//    	Map<Integer, Integer> cnt = new HashMap<>();
//    	
//    	for (int i = 0; i < nums.length; ++i) {
//    		if (!lf.containsKey(nums[i])) lf.put(nums[i], i);
//    		rt.put(nums[i], i);
//    		cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
//    	}
//    	
//    	int degree = Collections.max(cnt.values());
//    	int ans = Integer.MAX_VALUE;
//    	for (int key : cnt.keySet()) {
//    		if (degree == cnt.get(key)) {
//    			ans = Math.min(ans, rt.get(key) - lf.get(key) + 1);
//    		}
//    	}
//    	return ans;
//    }
	
	public static void main(String[] args) {
		SolutionDay15_L0697 day = new SolutionDay15_L0697();
		int[] nums = {2,1,1,2,1,3,3,3,1,3,1,3,2};
		System.out.println(day.findShortestSubArray(nums));
	}
}
