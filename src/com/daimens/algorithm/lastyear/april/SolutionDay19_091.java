package com.daimens.algorithm.april;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong 
 * 91.Decode Ways
 * 
 *         A message containing letters from A-Z is being encoded to numbers
 *         using the following mapping:
 * 
 *         'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing
 *         digits, determine the total number of ways to decode it.
 * 
 *         For example, Given encoded message "12", it could be decoded as "AB"
 *         (1 2) or "L" (12).
 * 
 *         The number of ways decoding "12" is 2.
 *
 */
public class SolutionDay19_091 {
	
	
//	public int numDecodings(String s) {
//        return helper(s);
//    }
//	
//	Map<String, Integer> map = new HashMap<>();
//	private int helper(String s){
//		
//		if(map.containsKey(s)) return map.get(s);
//		
//		int count = 0;
//		if (s.length() == 0) return 0;
//		else if (s.length() == 1){
//			if (isLegal(s.charAt(0)-'0')) return 1;
//		}
//		else if (s.length() ==2){
//			int value = 0;
//			if (isLegal(s.charAt(0)-'0') && isLegal(s.charAt(1)-'0')){
//				value ++;
//			}
//			
//			if(s.charAt(0) - '0' != 0 && isLegal((s.charAt(0)-'0') * 10 + (s.charAt(1) - '0'))){
//				value ++;
//			}
//			
//			return value;
//		}
//		else{
//			char[] ss = s.toCharArray();
//			int last = ss.length - 1;
//			
//			int num = ss[last] - '0';
//			
//			if(isLegal(num)){
//				count = helper(s.substring(0, last));
//				map.put(s.substring(0,last), count);
//			}
//			
//			if (ss.length >= 2){
//				num = (ss[last-1] -'0') * 10 + ss[last] - '0';
//				if (ss[last-1] - '0' != 0 && isLegal(num)){
//					count += helper(s.substring(0, last - 1));
//					map.put(s.substring(0,last-1), count);
//				}
//			}
//		}
//		
//		return count;
//	}
//	
	
	private boolean isLegal(int num){
		if (num >= 1 && num <= 26) return true;
		return false;
	}
	
	// 从后往前构造 不需要考虑各种0的情况
//	public int numDecodings(String s) {
//		int n = s.length();
//		if (n ==0) return 0;
//		
//		int[] dp = new int[n + 1];
//		dp[0] = s.charAt(0) - '0' != 0 ? 1 : 0;
//		dp[1] = s.charAt(0) - '0' != 0 ? 1 : 0;
//		
//		for (int i = 1; i < n; i++) {
//			
//			if (s.charAt(i)-'0' == 0){
//				dp[i+1] = dp[i-1];
//				continue;
//			}
//			int num = s.charAt(i)-'0' +( s.charAt(i-1) - '0' ) * 10;
//			dp[i+1] = s.charAt(i-1) - '0' != 0 && isLegal(num) ? dp[i] + dp[i-1] : dp[i];
//		}
//		
//		return dp[n];
//	}
	
	public int numDecodings(String s) {
		int n = s.length();
		if (n == 0) return 0;
		
		int[] dp = new int[n + 1];
		dp[n] = 1;
		dp[n-1] = s.charAt(n-1) != '0' ? 1: 0;
		
		for (int i = n-2; i >= 0; i--){
			if (s.charAt(i) == '0') continue;
			else{
				dp[i] = (Integer.parseInt(s.substring(i, i+2)) <= 26) ? dp[i+1] + dp[i+2] : dp[i+1];
			}
		}
		
		return dp[0];
	}
	
	public static void main(String[] args) {
		SolutionDay19_091 day = new SolutionDay19_091();
		day.numDecodings("101");
	}
}
