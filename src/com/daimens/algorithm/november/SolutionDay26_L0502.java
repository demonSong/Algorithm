package com.daimens.algorithm.november;

import java.util.Stack;

public class SolutionDay26_L0502 {
	
//	public int[] asteroidCollision(int[] asteroids) {
//		int n = asteroids.length;
//		if (n == 0) return new int[] {};
//		if (n == 1) return asteroids;
//		
//		boolean prev = isPos(asteroids[0]);
//		List<Integer> ans = new ArrayList<>();
//		
//		int i = 1;
//		
//		for (i = 1; i < n; ++i) {
//			boolean curr = isPos(asteroids[i]);
//			if (prev == true && curr == false) {
//				break;
//			}
//			prev = curr;
//		}
//		
//		if (i == n) {
//			return asteroids;
//		}
//		
//		for (int j = 0; j <= i - 2; ++j) ans.add(asteroids[j]);
//		if (Math.abs(asteroids[i - 1]) > Math.abs(asteroids[i])) {
//			ans.add(asteroids[i - 1]);
//		}
//		else if (Math.abs(asteroids[i - 1]) < Math.abs(asteroids[i])) {
//			ans.add(asteroids[i]);
//		}
//		
//		for (int j = i + 1; j < n; ++j) ans.add(asteroids[j]);
//		
//		Integer[] aa = ans.toArray(new Integer[0]);
//		int[] tmp = new int[aa.length];
//		for (int j = 0; j < aa.length; ++j) {
//			tmp[j] = aa[j];
//		}
//		
//        return tmp;
//    }
	
	public int[] asteroidCollision(int[] asteroids) {
		int n = asteroids.length;
		if (n == 0) return new int[] {};
		if (n == 1) return asteroids;
		
		Stack<Integer> stack = new Stack<>();
		stack.push(asteroids[0]);
		
		for (int i = 1; i < n; ++i) {
			
			if (stack.isEmpty()) {
				stack.push(asteroids[i]);
				continue;
			}
			
			int prev = stack.peek();
			boolean pf = isPos(prev);
			
			int curr =  asteroids[i];
			boolean cf = isPos(curr);
			
			if (pf == true && cf == false) {
				if (Math.abs(prev) > Math.abs(curr)) {
					
				}
				else if (Math.abs(prev) < Math.abs(curr)) {
					stack.pop();
					i = i - 1;
				}
				else {
					stack.pop();
				}
			}
			else {
				stack.push(curr);
			}
		}
	
		
		Integer[] aa = stack.toArray(new Integer[0]);
		
		int[] ans = new int[aa.length];
		for (int j = 0; j < aa.length; ++j) {
			ans[j] = aa[j];
		}
		
        return ans;
    }
	
	boolean isPos(int num) {
		return num >= 0;
	}
	
	public static void main(String[] args) {
		SolutionDay26_L0502 day = new SolutionDay26_L0502();
		int[] num = {1, -2, -2};
		System.out.println(day.asteroidCollision(num));
	}
}
