package com.daimens.algorithm.december;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SolutionDay03_L0739 {
	
	public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> mem = new HashMap<>();
        for (int i = 0; i < n; ++i) {
        	while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
        		int nxt = stack.pop();
        		mem.put(nxt, i);
        	}
        	stack.push(i);
        }
        
        for (int i = 0; i < n; ++i) {
        	if (mem.containsKey(i)) {
        		ans[i] = mem.get(i) - i;
        	}
        	else ans[i] = 0;
        }
		return ans;
    }

	public static void main(String[] args) {
		SolutionDay03_L0739 day = new SolutionDay03_L0739();
	}
	
}
