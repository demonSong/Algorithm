package com.daimens.algorithm.april;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 
 *         128.Longest Consecutive Sequence
 * 
 *         Given an unsorted array of integers, find the length of the longest
 *         consecutive elements sequence.
 * 
 *         For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive
 *         elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * 
 *         Your algorithm should run in O(n) complexity.
 *
 */
public class SolutionDay23_128 {
	
	// 0 1 1 2 not work
//	public int longestConsecutive(int[] nums) {
//        
//        if (nums.length == 0) return 0;
//        
//		Arrays.sort(nums);
//		int count = 1;
//		int max = 1;
//		for (int i = 1; i < nums.length; i++){
//			
//			if (nums[i] - nums[i-1] != 1) {
//				count = 1;
//				continue;
//			}
//			
//			count ++;
//			max = Math.max(count, max);
//		}
//		
//        return max;
//    }
	
	// 更新两头boundary
//	public int longestConsecutive(int[] nums) {
//        if (nums.length == 0) return 0;
//        Map<Integer,Integer> map = new HashMap<>();
//        
//        for (int i = 0; i < nums.length; i++){
//        	int key = nums[i];
//        	if (map.containsKey(key)) continue;
//        	
//        	int value = 1;
//        	if (map.containsKey(key-1)){
//        		int count = map.get(key-1);
//        		value += count;
//        		//map.put(key, value);
//        	}
//        	
//        	int tmp = key + 1;
//        	while (map.containsKey(tmp)){
//        		//map.put(tmp, map.get(tmp) + value);
//        		tmp ++;
//        	}
//        	
//        	if (map.containsKey(tmp-1)) map.put(tmp-1, map.get(tmp-1) + value);
//        	
//        	map.put(key, value);
//        }
//        
//        int max = 0;
//        for (int key : map.keySet()){
//        	max = Math.max(max, map.get(key));
//        }
//        
//        return max;
//    }
	
	public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        
        int max = 0;
        for (int i = 0; i < nums.length; i++){
        	int key = nums[i];
        	if (map.containsKey(key)) continue;
        	
        	int left = map.containsKey(key-1) ? map.get(key-1) : 0;
        	int right = map.containsKey(key + 1) ? map.get(key + 1) : 0;
        	
        	int val = left + right + 1;
        	map.put(key, val);
        	
        	max = Math.max(max, val);
        	
        	map.put(key-left, val);
        	map.put(key+right, val);
        }
        
        return max;
    }

}
