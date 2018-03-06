package com.daimens.algorithm.august;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SolutionDay13_L0659 {
	
//	public boolean isPossible(int[] nums) {
//		
//        int[] map = new int[10000 + 16];
//        for (int i = 0; i < nums.length; ++i){
//        	map[nums[i]] ++;
//        }
//        int len = nums.length;
//        int n = nums.length;
//        while (len != 0){
//        	int max = -1;
//        	int id = -1;
//        	for (int i = 0; i < map.length; ++i){
//        		if (map[i] > max){
//        			max = map[i];
//        			id = i;
//        		}
//        	}
//        	//if (id + 1 >= n || id + 2 >= n) return false;
//        	
//        	if (map[id + 1] != 0 || map[id + 2] != 0) return false;
//        	else{
//              int mm = Math.max(map[id + 1], map[id + 2]);
//        		map[id] -= mm;
//        		map[id + 1] -= mm;
//        		map[id + 2] -= mm;
//        		int j = 0;
//        		int cnt = 3;
//        		while (id + j < n){
//        			map[id + j] -= Math.min(mm, map[id + j]);
//        			cnt ++;
//        		}
//        		len -= cnt;
//        	}
//        }
//        return true;
//    }
	
	public boolean isPossible(int[] nums) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		Map<Integer, Integer> append = new HashMap<>();
		for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
		for (int i : nums){
			if (map.get(i) == 0) continue;
			if (append.getOrDefault(i, 0) > 0){  // 先拼接
				append.put(i, append.get(i) - 1);
				append.put(i + 1, append.getOrDefault(i + 1, 0) + 1);
			}
			else if (map.getOrDefault(i + 1, 0) > 0 && map.getOrDefault(i + 2, 0) > 0){ // 再独立
				map.put(i + 1, map.get(i + 1) - 1);
				map.put(i + 2, map.get(i + 2) - 1);
				append.put(i + 3, append.getOrDefault(i + 3, 0) + 1);
			}
			else return false;
			map.put(i, map.get(i) - 1);
		}
		return true;
	}
	
	public static void main(String[] args) {
		SolutionDay13_L0659 day = new SolutionDay13_L0659();
		int[] nums = {1,2,3,3,4,5};
		System.out.println(day.isPossible(nums));
	}

}
