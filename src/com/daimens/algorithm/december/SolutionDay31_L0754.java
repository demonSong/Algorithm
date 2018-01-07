package com.daimens.algorithm.december;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class SolutionDay31_L0754 {
	
//    public int reachNumber(int target) {
//    	Queue<Integer> queue = new ArrayDeque<>();
//    	Set<Integer> vis   = new HashSet<>();
//    	queue.offer(0);
//    	vis.add(0);
//    	if (vis.contains(target)) return 0;
//    	int step = 0;
//    	while (!queue.isEmpty()) {
//    		int size = queue.size();
//    		for (int i = 0; i < size; ++i) {
//    			int now = queue.poll();
//    			if (now == target) return step;
//    			//if (!vis.contains(now + (step + 1))) {
//    				queue.offer(now + step + 1);
////    				vis.add(now + step + 1);
//    			//}
//    			//if (!vis.contains(now - (step + 1))) {
//    				queue.offer(now - step - 1);
////    				vis.add(now - step - 1);
//    			//}
//    		}
//    		step ++;
//    	}
//    	return -1;
//    }
    
//    public int reachNumber(int target) {
//    	return go(0, target, 0);
//    }
//    
//    public int go(int from, int target, int step) {
//    	if (from == target) return step;
//    	else {
//    		step += 1;
//        	int ans = Integer.MAX_VALUE;
//        	
//        	int left= go(from + step, target, step + 1);
//        	return ans;
//    	}
//    }
	
	public int reachNumber(int target) {
        target = Math.abs(target);
		int sum = 0;
        int n   = 0;
        while (sum < target){
            n ++;
            sum += n;
        }
		if (sum == target) return n;
		int res = sum - target;
		if ((res & 1) == 0) {
			return n;
		}
		else {
			return n + ((n & 1) == 0 ? 1 : 2);
		}
	}
    
	
	public static void main(String[] args) {
		SolutionDay31_L0754 day = new SolutionDay31_L0754();
		System.out.println(day.reachNumber(1));
	}
}
