package com.daimens.algorithm.april;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong 514.Freedom Trail
 * 
 *         In the video game Fallout 4, the quest "Road to Freedom" requires
 *         players to reach a metal dial called the "Freedom Trail Ring", and
 *         use the dial to spell a specific keyword in order to open the door.
 * 
 *         Given a string ring, which represents the code engraved on the outer
 *         ring and another string key, which represents the keyword needs to be
 *         spelled. You need to find the minimum number of steps in order to
 *         spell all the characters in the keyword.
 * 
 *         Initially, the first character of the ring is aligned at 12:00
 *         direction. You need to spell all the characters in the string key one
 *         by one by rotating the ring clockwise or anticlockwise to make each
 *         character of the string key aligned at 12:00 direction and then by
 *         pressing the center button. At the stage of rotating the ring to
 *         spell the key character key[i]: You can rotate the ring clockwise or
 *         anticlockwise one place, which counts as 1 step. The final purpose of
 *         the rotation is to align one of the string ring's characters at the
 *         12:00 direction, where this character must equal to the character
 *         key[i]. If the character key[i] has been aligned at the 12:00
 *         direction, you need to press the center button to spell, which also
 *         counts as 1 step. After the pressing, you could begin to spell the
 *         next character in the key (next stage), otherwise, you've finished
 *         all the spelling. Example:
 * 
 * 
 *         Input: ring = "godding", key = "gd" Output: 4 Explanation: For the
 *         first key character 'g', since it is already in place, we just need 1
 *         step to spell this character. For the second key character 'd', we
 *         need to rotate the ring "godding" anticlockwise by two steps to make
 *         it become "ddinggo". Also, we need 1 more step for spelling. So the
 *         final output is 4. Note: Length of both ring and key will be in range
 *         1 to 100. There are only lowercase letters in both strings and might
 *         be some duplcate characters in both strings. It's guaranteed that
 *         string key could always be spelled by rotating the string ring.
 *
 */
public class SolutionDay20_514 {
	
	//当前的选择将影响下一轮，所以不能简单的用贪心算法求解全局最优
//	public int findRotateSteps(String ring, String key) {
//		
//		if (ring.length() == 0 || key.length() == 0) return 0;
//		
//		int len = key.length();
//		
//		for (int i = 0; i < key.length(); i++){
//			
//			int fir = 0, lst = ring.length() - 1;
//			while (ring.charAt(fir) != key.charAt(i)) fir ++;
//			while (ring.charAt(lst) != key.charAt(i)) lst --;
//			
//			int index = Math.min(fir, ring.length() - lst);
//			len += index;
//			if (fir < ring.length() - lst)
//				ring = ring.substring(fir, ring.length()) + ring.substring(0,fir);
//			else
//				ring = ring.substring(lst, ring.length()) + ring.substring(0,lst);
//		}
//        return len;
//    }
	
	
//	public int findRotateSteps(String ring, String key) {
//		if (ring.length() == 0 || key.length() == 0) return 0;
//		return helper(ring, key, 0);
//	}
	
//	Map<String, Map<Integer, Integer>> map = new HashMap<>();
//	private int helper(String ring, String key, int start){
//
//		int value = 0;
//		if (start == key.length()) return 0;
//		else{
//			
//			if (map.containsKey(ring) && map.get(ring).containsKey(start)){
//				return map.get(ring).get(start);
//			}
//			
//			char tmp = key.charAt(start);
//			if (ring.charAt(0) == tmp) {
//				value = helper(ring, key, start+1) + 1;
//				Map<Integer, Integer> tmpMap= new HashMap<>();
//				tmpMap.put(start, value);
//				map.put(ring,tmpMap);
//				
//				return value;
//			}
//			else{
//				int fir = 0, lst = ring.length()-1;
//				while (ring.charAt(fir) != key.charAt(start)) fir ++;
//				while (ring.charAt(lst) != key.charAt(start)) lst --;
//				
//				String ring1 = ring.substring(fir, ring.length()) + ring.substring(0,fir);
//				String ring2 = ring.substring(lst, ring.length()) + ring.substring(0,lst);
//				
//				int a1 = helper(ring1, key, start+1) + fir+1;
//				int a2 = helper(ring2, key, start+1) + ring.length()- lst + 1;
//				
//				value = Math.min(a1,a2);
//				
//				Map<Integer,Integer> tmpMap = new HashMap<>();
//				tmpMap.put(start, value);
//				map.put(ring, tmpMap);
//			}
//			return value;
//		}
//	}
	
//	Map<String, Map<Integer, Integer>> map = new HashMap<>();
//	private int helper(String ring, String key, int start) {
//
//		int value = 0;
//		if (start == key.length())
//			return 0;
//		else {
//
//			if (map.containsKey(ring) && map.get(ring).containsKey(start)) {
//				return map.get(ring).get(start);
//			}
//
//			int fir = 0, lst = ring.length() - 1;
//			while (ring.charAt(fir) != key.charAt(start))
//				fir++;
//			while (ring.charAt(lst) != key.charAt(start))
//				lst--;
//
//			String ring1 = ring.substring(fir, ring.length()) + ring.substring(0, fir);
//			String ring2 = ring.substring(lst, ring.length()) + ring.substring(0, lst);
//
//			int a1 = helper(ring1, key, start + 1) + fir + 1;
//			int a2 = helper(ring2, key, start + 1) + ring.length() - lst + 1;
//
//			value = Math.min(a1, a2);
//
//			Map<Integer, Integer> tmpMap = new HashMap<>();
//			tmpMap.put(start, value);
//			map.put(ring, tmpMap);
//			return value;
//		}
//	}
	
	public int findRotateSteps(String ring, String key) {
		
		if (ring.length() == 0 || key.length() == 0) return 0;
		
		int n = ring.length();
		int m = key.length();
		int[][] dp = new int[m+1][n];
		
		for (int i = m - 1; i >= 0; i--){
			for(int j = 0; j < n; j++){
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = 0; k < n; k++){
					if (ring.charAt(k) == key.charAt(i)){
						int diff = Math.abs(j-k);
						int step = Math.min(diff, n-diff);
						dp[i][j] = Math.min(dp[i][j], step + dp[i+1][k]);
					}
				}
			}
		}
		
		return dp[0][0] + m;
	}
	
	public static void main(String[] args) {
		SolutionDay20_514 day = new SolutionDay20_514();
		day.findRotateSteps("xrrakuulnczywjs", "jrlucwzakzussrlckyjjsuwkuarnaluxnyzcnrxxwruyr");
		System.out.println();
	}

}
