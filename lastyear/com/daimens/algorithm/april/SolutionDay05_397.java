package com.daimens.algorithm.april;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 397.Integer Replacement
 * Given a positive integer n and you can do operations as follow:
 * 1. If n is even, replace with n/2.
 * 2. If n is odd, you can replace n with either n+1 or n-1.
 * 
 * What is the minimum number of replacements needed for n to become 1?
 * 
 * Example 1:
 * Input:
 * 8
 * Output:
 * 3
 * Explanation:
 * 8 -> 4 -> 2 -> 1
 * 
 * Example 2:
 * Input:
 * 7
 * Output:
 * 4
 * Explanation:
 * 7 -> 8 -> 4 -> 2 -> 1
 * or
 * 7 -> 6 -> 3 -> 2 -> 1
 * 
 *
 */
public class SolutionDay05_397 {
	
//	public int integerReplacement(int n) {
//		if(n <= 1) return 0;
//		
//		int[] dp = new int[n+2]; // 忽略dp[0]
//		dp[1] = 0;
//		
//		for (int i = 2; i <= n; i++){
//			
//			if(i % 2 == 0){
//				dp[i] = 1 + dp[i/2];
//			}else {
//				dp[i+1] = 1 + dp[(i+1) /2];
//				dp[i] = Math.min(dp[i-1], dp[i+1])+1;
//			}
//			
//		}
//		return dp[n];
//    }
	
	public int integerReplacement(int n) {
		if(n == 1) return 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>() {
			{
				put(1, 0);
				put(2, 1);
			}
		};
		return helper(n, map);
	}
	
	private int helper(int n,Map<Integer,Integer> map){
		if(map.containsKey(n)){
			map.get(n);
		}
		
		int count = 0;
		while ( (n % 2) == 0){
			n = n /2;
			count++;
		}
		
		if (n != 1){
			count += Math.min(helper(n+1,map), helper(n-1,map)) + 1;
		}
		
		map.put(n, count);
		
		return count;
	}
	
//	private int helper(int n, Map<Integer, Integer> map) {
//        if (map.containsKey(n)) {
//            return map.get(n);
//        }
//        
//        int steps = -1;
//        if (n % 2 == 0) {
//            steps = helper(n / 2, map) + 1;// 这里也map的好处
//        } else {
//            steps = Math.min(helper((n - 1), map) + 1, helper(1 + (n - 1) / 2, map) + 2);
//        }
//        
//        map.put(n, steps);
//        
//        return steps;
//    }
	
	
	
	
	
	
	public static void main(String[] args) {
		SolutionDay05_397 day = new SolutionDay05_397();
		day.integerReplacement(7);
	}

}
